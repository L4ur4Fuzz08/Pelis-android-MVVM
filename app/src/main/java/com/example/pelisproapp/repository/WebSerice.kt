package com.example.pelisproapp.repository

import com.example.pelisproapp.BuildConfig
import com.example.pelisproapp.data.remote.interceptor.ApiKeyInterceptor
import com.example.pelisproapp.data.remote.model.PeliculaList
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.logging.Level

interface WebSerice {
    @GET("movies/upcoming")
    suspend fun getUpcomingMovies(): PeliculaList
    @GET("movies/top_rated")
    suspend fun getTopRatedMovies(): PeliculaList
    @GET("movies/popular")
    suspend fun getPopularMovies(): PeliculaList
}

object RetrofitClient{
    private val loggingInterceptor ={
        HttpLoggingInterceptor().apply {
            if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor(BuildConfig.API_KEY))
        .addInterceptor(loggingInterceptor())
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