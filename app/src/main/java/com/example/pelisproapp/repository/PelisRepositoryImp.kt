package com.example.pelisproapp.repository

import com.example.pelisproapp.data.remote.PeliculaDataSource
import com.example.pelisproapp.data.remote.model.PeliculaList

class PelisRepositoryImp(private val dataSource:PeliculaDataSource): PelisRepository {
    override suspend fun getUpcomingMovies(): PeliculaList {
        return dataSource.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): PeliculaList {
        return dataSource.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): PeliculaList {
        return dataSource.getPopularMovies()
    }


}