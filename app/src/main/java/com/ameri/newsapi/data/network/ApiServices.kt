package com.ameri.newsapi.data.network

import com.ameri.newsapi.data.models.ResponseTopHeadlines
import com.ameri.newsapi.util.constant.US
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiServices {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlinesData(@Query("country") country : String = US): Response<ResponseTopHeadlines>

}