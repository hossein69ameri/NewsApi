package com.ameri.domain.repository

import com.ameri.domain.model.NewsData
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getData(endpoint: String, category: String? = null): NewsData

    suspend fun loadNewsByCategory(endpoint: String, category: String? = null): Flow<List<NewsData.Data>>

}
