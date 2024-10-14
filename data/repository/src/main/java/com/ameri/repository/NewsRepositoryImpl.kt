package com.ameri.repository

import com.ameri.domain.model.NewsData
import com.ameri.domain.repository.NewsRepository
import com.ameri.mapper.toNews
import com.ameri.remote.network.ApiServices
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private  val apiServices: ApiServices) : NewsRepository {

    override suspend fun getTopStoriesData(): NewsData = apiServices.getTopStoriesData().toNews()

    override suspend fun getSportsData(): NewsData = apiServices.getSportsData().toNews()

    override suspend fun getFoodiesData(): NewsData = apiServices.getFoodiesData().toNews()

    override suspend fun getTechnologyData(): NewsData = apiServices.getTechnologyData().toNews()

}