package com.example.pelisproapp.presentacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pelisproapp.repository.PelisRepository

class PeliculaViewModelFactory(private val repo: PelisRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PelisRepository::class.java).newInstance(repo)
    }
}