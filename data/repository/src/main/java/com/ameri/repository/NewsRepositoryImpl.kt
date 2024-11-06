package com.ameri.repository

import android.util.Log
import com.ameri.domain.model.NewsData
import com.ameri.domain.repository.NewsRepository
import com.ameri.local.database.NewsDao
import com.ameri.mapper.toNewsData
import com.ameri.mapper.toNewsEntity
import com.ameri.mapper.toRemoteNews
import com.ameri.remote.network.ApiServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val newsDao: NewsDao
) : NewsRepository {

    override suspend fun getData(endpoint: String, category: String?): NewsData =
        withContext(Dispatchers.IO) {
            val categoryToUse = if (endpoint == "top") "top" else category!!
            val cachedNewsData = newsDao.loadNewsByCategory(categoryToUse)
                .firstOrNull()?.map { it.toNewsData() } ?: emptyList()

            return@withContext if (cachedNewsData.isEmpty()) {
                try {
                    val response = apiServices.getData(endpoint, category = category)
                    val freshNewsData = response.toRemoteNews()

                    freshNewsData.data?.let { newData ->
                        newData.forEach { newsItem ->
                            newsDao.saveNews(newsItem.toNewsEntity(categoryToUse))
                        }
                    }

                    freshNewsData
                } catch (exception: Exception) {
                    NewsData(data = emptyList())
                }
            } else {
                val newsData = NewsData(data = cachedNewsData)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val response = apiServices.getData(endpoint, category = category)
                        val freshNewsData = response.toRemoteNews()

                        freshNewsData.data?.let { newData ->
                            newsDao.deleteNewsByCategory(categoryToUse)
                            newData.forEach { newsItem ->
                                newsDao.saveNews(newsItem.toNewsEntity(categoryToUse))
                            }
                        }
                    } catch (exception: Exception) {
                        Log.e("NewsRepository", "Error fetching data from API: ${exception.message}")
                    }
                }
                newsData
            }
        }
}
