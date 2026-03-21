package com.example.pelisproapp.data.remote.interceptor

import com.example.pelisproapp.BuildConfig
import com.example.pelisproapp.repository.WebSerice
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

object RetrofitClient{

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor(BuildConfig.API_KEY))
        .build()
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient)
            .build()
            .create(WebSerice::class.java)
    }

}