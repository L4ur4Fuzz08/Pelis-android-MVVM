package com.example.pelisproapp.repository

import com.example.pelisproapp.data.remote.PeliculaDataSource
import com.example.pelisproapp.data.remote.model.PeliculaList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PelisRepositoryImp(private val dataSource:PeliculaDataSource): PelisRepository {
    override suspend fun getUpcomingMovies(): PeliculaList = withContext(Dispatchers.IO) {
     dataSource.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): PeliculaList = withContext(Dispatchers.IO)  {
        dataSource.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): PeliculaList = withContext(Dispatchers.IO)  {
         dataSource.getPopularMovies()
    }


}