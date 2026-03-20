package com.example.pelisproapp.repository

import com.example.pelisproapp.data.remote.model.PeliculaList

class PelisRepositoryImp: PelisRepository {
    override suspend fun getUpcomingMovies(): PeliculaList {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovies(): PeliculaList {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularMovies(): PeliculaList {
        TODO("Not yet implemented")
    }


}