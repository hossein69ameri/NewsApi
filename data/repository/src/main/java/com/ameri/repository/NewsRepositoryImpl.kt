package com.ameri.repository

import com.ameri.domain.model.NewsData
import com.ameri.domain.repository.NewsRepository
import com.ameri.mapper.toRemoteNews
import com.ameri.remote.network.ApiServices
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private  val apiServices: ApiServices) : NewsRepository {
    override suspend fun getData(endpoint: String, category: String?): NewsData = apiServices.getData(endpoint, category = category).toRemoteNews()
}