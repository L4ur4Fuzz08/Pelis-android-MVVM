package com.example.pelisproapp.repository

import com.example.pelisproapp.data.remote.model.PeliculaList
import retrofit2.http.GET

interface WebSerice {
    @GET("movies/upcoming")
    suspend fun getUpcomingMovies(): PeliculaList
    @GET("movies/top_rated")
    suspend fun getTopRatedMovies(): PeliculaList
    @GET("movies/popular")
    suspend fun getPopularMovies(): PeliculaList
}