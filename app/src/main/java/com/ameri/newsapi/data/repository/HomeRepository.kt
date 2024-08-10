package com.ameri.newsapi.data.repository

import com.ameri.newsapi.data.network.ApiServices
import javax.inject.Inject

class HomeRepository @Inject constructor(private  val apiServices: ApiServices) {
    suspend fun getTopStoriesData() = apiServices.getTopStoriesData()
    suspend fun getSportsData() = apiServices.getSportsData()
    suspend fun getFoodiesData() = apiServices.getFoodiesData()
    suspend fun getTechnologyData() = apiServices.getTechnologyData()
}