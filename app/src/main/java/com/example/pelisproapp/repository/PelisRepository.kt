package com.example.pelisproapp.repository

import com.example.pelisproapp.data.remote.model.PeliculaList
import com.example.pelisproapp.presentacion.model.Pelicula

interface PelisRepository {
   suspend fun getUpcomingMovies(): List<Pelicula>
   suspend fun getTopRatedMovies(): List<Pelicula>
   suspend fun getPopularMovies(): List<Pelicula>
}