package com.ameri.domain.repository

import com.ameri.domain.models.ResponseData
import retrofit2.Response

interface NewsRepository {
    suspend fun getTopStoriesData(): Response<ResponseData>
    suspend fun getSportsData(): Response<ResponseData>
    suspend fun getFoodiesData(): Response<ResponseData>
    suspend fun getTechnologyData(): Response<ResponseData>
}
