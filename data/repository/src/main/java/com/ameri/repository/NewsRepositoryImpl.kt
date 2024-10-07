package com.ameri.repository

import com.ameri.remote.models.ResponseData
import com.ameri.domain.repository.NewsRepository
import com.ameri.remote.network.ApiServices
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private  val apiServices: ApiServices) : NewsRepository {

    override suspend fun getTopStoriesData(): Response<ResponseData> =  apiServices.getTopStoriesData()

    override suspend fun getSportsData(): Response<ResponseData> = apiServices.getSportsData()

    override suspend fun getFoodiesData(): Response<ResponseData> = apiServices.getFoodiesData()

    override suspend fun getTechnologyData(): Response<ResponseData> = apiServices.getTechnologyData()

}