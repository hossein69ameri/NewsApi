package com.ameri.repository

import com.ameri.domain.model.NewsData
import com.ameri.domain.repository.NewsRepository
import com.ameri.local.database.NewsDao
import com.ameri.mapper.toNewsData
import com.ameri.mapper.toNewsEntity
import com.ameri.mapper.toRemoteNews
import com.ameri.remote.ALL
import com.ameri.remote.TOP
import com.ameri.remote.network.ApiServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val newsDao: NewsDao
) : NewsRepository {

    override suspend fun getData(endpoint: String, category: String?): NewsData {
        val categoryToUse = if (endpoint == TOP) TOP else category ?: ALL
        try {
            val response = apiServices.getData(endpoint, category = category)
            val freshNewsData = response.toRemoteNews()
            freshNewsData.data?.let { newData ->
                newsDao.deleteNewsByCategory(categoryToUse)
                newData.forEach { newsItem ->
                    newsDao.saveNews(newsItem.toNewsEntity(categoryToUse))
                }
            }
            return freshNewsData
        } catch (exception: Exception) {
            return NewsData(data = emptyList())
        }
    }


    override suspend fun loadNewsByCategory(endpoint: String, category: String?): Flow<List<NewsData.Data>> {
        val categoryToUse = if (endpoint == TOP) TOP else category ?: ALL
        return newsDao.loadNewsByCategory(categoryToUse).map { newsEntityList ->
            newsEntityList.map { it.toNewsData() }
        }
    }
}
