package com.example.pelisproapp.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        //se crea una nueva url añadiendo el parametro apikey
        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()
        val requestBuilder = originalRequest.newBuilder().url(url)
        return chain.proceed(requestBuilder.build())
    }
}