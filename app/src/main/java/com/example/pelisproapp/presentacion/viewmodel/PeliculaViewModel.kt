package com.example.pelisproapp.presentacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pelisproapp.core.Resource
import com.example.pelisproapp.data.remote.model.PeliculaList
import com.example.pelisproapp.presentacion.PelisCollection
import com.example.pelisproapp.presentacion.model.Pelicula
import com.example.pelisproapp.repository.PelisRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PeliculaViewModel(private val repo: PelisRepository) : ViewModel() {

    private val _movieState = MutableStateFlow<Resource<PelisCollection<List<Pelicula>, List<Pelicula>, List<Pelicula>>>>(Resource.Loading)
    val movieState: StateFlow<Resource<PelisCollection<List<Pelicula>, List<Pelicula>, List<Pelicula>>>> = _movieState


    init {

        fetchUpcomingMovies()
    }


    fun fetchUpcomingMovies() {
        /*se abre una corrutina en el scope viewmodel para las llamadas del datasource*/
        viewModelScope.launch {
            _movieState.value = Resource.Loading
            try {
                //se llaman los 3 metodos del repository

                val data = PelisCollection(
                    repo.getUpcomingMovies(),
                    repo.getTopRatedMovies(),
                    repo.getPopularMovies()
                )
                _movieState.value = Resource.Success(data)
            } catch (e: Exception) {
                _movieState.value = Resource.Failure(e)
            }
        }

    }
}