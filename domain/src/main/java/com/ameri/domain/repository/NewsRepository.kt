package com.ameri.domain.repository

import com.ameri.domain.model.NewsData

interface NewsRepository {
    suspend fun getTopStoriesData(): NewsData
    suspend fun getSportsData(): NewsData
    suspend fun getFoodiesData(): NewsData
    suspend fun getTechnologyData(): NewsData
}
