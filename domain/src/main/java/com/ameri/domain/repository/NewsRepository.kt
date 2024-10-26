package com.ameri.domain.repository

import com.ameri.domain.model.NewsData

interface NewsRepository {
    suspend fun getData(endpoint: String, category: String? = null): NewsData
}
