package com.example.pelisproapp.presentacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.pelisproapp.core.Resource
import com.example.pelisproapp.repository.PelisRepository
import kotlinx.coroutines.Dispatchers

class PeliculaViewModel(private val repo: PelisRepository): ViewModel() {



    fun fetchUpcomingMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading)
        try {
            emit(Resource.Success(repo.getUpcomingMovies()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}