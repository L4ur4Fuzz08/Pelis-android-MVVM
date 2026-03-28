package com.example.pelisproapp.repository

import com.example.pelisproapp.data.remote.PeliculaDataSource
import com.example.pelisproapp.data.remote.model.PeliculaList
import com.example.pelisproapp.presentacion.model.Pelicula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PelisRepositoryImp(private val dataSource:PeliculaDataSource): PelisRepository {
    override suspend fun getUpcomingMovies(): List<Pelicula> = withContext(Dispatchers.IO) {
     dataSource.getUpcomingMovies().results.map {
         it.toPelicula()
     }
    }

    override suspend fun getTopRatedMovies(): List<Pelicula> = withContext(Dispatchers.IO)  {
        dataSource.getTopRatedMovies().results.map {
            it.toPelicula()
        }
    }

    override suspend fun getPopularMovies(): List<Pelicula> = withContext(Dispatchers.IO)  {
         dataSource.getPopularMovies().results.map {
             it.toPelicula()
         }
    }


}