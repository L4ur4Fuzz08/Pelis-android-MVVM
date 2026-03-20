package com.example.pelisproapp.repository

import com.example.pelisproapp.data.remote.model.PeliculaList

interface PelisRepository {
    fun getUpcomingMovies(): PeliculaList
    fun getTopRatedMovies(): PeliculaList
    fun getPopularMovies(): PeliculaList
}