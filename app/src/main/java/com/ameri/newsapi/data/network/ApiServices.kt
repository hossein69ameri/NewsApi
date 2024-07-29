package com.ameri.newsapi.data.network

import com.ameri.newsapi.data.models.ResponseData
import com.ameri.newsapi.util.constant.BITCOIN
import com.ameri.newsapi.util.constant.POPULARITY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("/v2/everything")
    suspend fun getEverythingData(@Query("q") q : String = BITCOIN, @Query("sortBy") sortBy : String = POPULARITY): Response<ResponseData>
}