package com.ameri.remote.network


import com.ameri.remote.API_KEY_THE_NEWS_API
import com.ameri.remote.API_TOKEN_KEY
import com.ameri.remote.CATEGORY_KEY
import com.ameri.remote.DEFAULT_LANGUAGE
import com.ameri.remote.Foodies_CATEGORY
import com.ameri.remote.LANGUAGE_KEY
import com.ameri.remote.SPORTS_CATEGORY
import com.ameri.remote.Techs_CATEGORY
import com.ameri.remote.models.ResponseData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("{endpoint}")
    suspend fun getData(
        @Path("endpoint") endpoint: String,
        @Query(API_TOKEN_KEY) apiToken: String = API_KEY_THE_NEWS_API,
        @Query(LANGUAGE_KEY) language: String = DEFAULT_LANGUAGE,
        @Query(CATEGORY_KEY) category: String? = null
    ): ResponseData
}