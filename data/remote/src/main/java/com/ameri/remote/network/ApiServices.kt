package com.ameri.remote.network


import com.ameri.domain.models.ResponseData
import com.ameri.remote.*
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