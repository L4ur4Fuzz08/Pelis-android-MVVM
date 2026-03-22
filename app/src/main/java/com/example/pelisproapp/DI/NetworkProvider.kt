package com.example.pelisproapp.di

import com.example.pelisproapp.data.remote.PeliculaDataSource
import com.example.pelisproapp.repository.PelisRepository
import com.example.pelisproapp.repository.PelisRepositoryImp
import com.example.pelisproapp.repository.RetrofitClient

object NetworkProvider {
    //se aplica un proveedor de dependecias
    private var repository: PelisRepository? = null
    // 1. Dependencias de red (singleton)
    private val webService by lazy {
        RetrofitClient.webService
    }

    // 2. DataSource (singleton)
    private val peliculaDataSource by lazy {
        PeliculaDataSource(webService)
    }

    // 3. Repository (singleton)
    fun provideRepository(): PelisRepository = repository?: createRepository()

    // 4. se crea el repo si no existe
    private fun createRepository(): PelisRepository{
        val nRepository = PelisRepositoryImp(peliculaDataSource)
        repository = nRepository
        return nRepository
    }

    fun destroy(){
        repository = null
    }


}