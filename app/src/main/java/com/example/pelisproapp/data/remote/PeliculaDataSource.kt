package com.example.pelisproapp.data.remote

import com.example.pelisproapp.data.remote.model.PeliculaList
import com.example.pelisproapp.repository.WebSerice

class PeliculaDataSource(private val webSerice: WebSerice) {

    suspend fun getUpcomingMovies(): PeliculaList{
        return webSerice.getUpcomingMovies()
    }

    suspend fun getTopRatedMovies(): PeliculaList{
        return webSerice.getTopRatedMovies()
    }

    suspend fun getPopularMovies(): PeliculaList{

        return webSerice.getPopularMovies()
    }
}