package com.example.pelisproapp.repository

import com.example.pelisproapp.data.remote.model.PeliculaList

interface PelisRepository {
   suspend fun getUpcomingMovies(): PeliculaList
   suspend fun getTopRatedMovies(): PeliculaList
   suspend fun getPopularMovies(): PeliculaList
}