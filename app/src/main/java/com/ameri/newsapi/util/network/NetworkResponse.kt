package com.ameri.newsapi.util.network

import retrofit2.Response

open class NetworkResponse<T>(private val response: Response<T>) {

    open fun safeApiCall(): NetworkRequest<T> {
        return try {
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    NetworkRequest.Success(data = body)
                } ?: NetworkRequest.Error(message = "Response body is null")
            } else {
                NetworkRequest.Error(message = "${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            NetworkRequest.Error(message = e.message.toString())
        }
    }
}
