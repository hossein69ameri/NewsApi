package com.ameri.data.repository

import com.ameri.data.network.ApiServices
import com.ameri.domain.models.ResponseData
import com.ameri.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private  val apiServices: ApiServices) : NewsRepository {

    override suspend fun getTopStoriesData(): Response<ResponseData> =  apiServices.getTopStoriesData()

    override suspend fun getSportsData(): Response<ResponseData> = apiServices.getSportsData()

    override suspend fun getFoodiesData(): Response<ResponseData> = apiServices.getFoodiesData()

    override suspend fun getTechnologyData(): Response<ResponseData> = apiServices.getTechnologyData()

}