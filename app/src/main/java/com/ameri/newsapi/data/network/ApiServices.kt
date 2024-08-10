package com.ameri.newsapi.data.network

import com.ameri.newsapi.data.models.ResponseData
import com.ameri.newsapi.util.constant.API_KEY_THE_NEWS_API
import com.ameri.newsapi.util.constant.API_TOKEN_KEY
import com.ameri.newsapi.util.constant.CATEGORY_KEY
import com.ameri.newsapi.util.constant.DEFAULT_LANGUAGE
import com.ameri.newsapi.util.constant.Foodies_CATEGORY
import com.ameri.newsapi.util.constant.LANGUAGE_KEY
import com.ameri.newsapi.util.constant.SPORTS_CATEGORY
import com.ameri.newsapi.util.constant.Techs_CATEGORY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("top")
    suspend fun getTopStoriesData(
        @Query(API_TOKEN_KEY) apiToken: String = API_KEY_THE_NEWS_API,
        @Query(LANGUAGE_KEY) language: String = DEFAULT_LANGUAGE
    ): Response<ResponseData>

    @GET("all")
    suspend fun getSportsData(
        @Query(API_TOKEN_KEY) apiToken: String = API_KEY_THE_NEWS_API,
        @Query(LANGUAGE_KEY) language: String = DEFAULT_LANGUAGE,
        @Query(CATEGORY_KEY) category: String = SPORTS_CATEGORY
    ): Response<ResponseData>

    @GET("all")
    suspend fun getFoodiesData(
        @Query(API_TOKEN_KEY) apiToken: String = API_KEY_THE_NEWS_API,
        @Query(LANGUAGE_KEY) language: String = DEFAULT_LANGUAGE,
        @Query(CATEGORY_KEY) category: String = Foodies_CATEGORY
    ): Response<ResponseData>

    @GET("all")
    suspend fun getTechnologyData(
        @Query(API_TOKEN_KEY) apiToken: String = API_KEY_THE_NEWS_API,
        @Query(LANGUAGE_KEY) language: String = DEFAULT_LANGUAGE,
        @Query(CATEGORY_KEY) category: String = Techs_CATEGORY
    ): Response<ResponseData>
}