package com.ameri.newsapi.data.network

import com.ameri.newsapi.data.models.ResponseTopStories
import com.ameri.newsapi.util.constant.API_KEY_THE_NEWS_API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("top")
    suspend fun getTopStoriesData(
        @Query("api_token") apiToken: String = API_KEY_THE_NEWS_API,
        @Query("language") language: String = "en"
    ): Response<ResponseTopStories>
}