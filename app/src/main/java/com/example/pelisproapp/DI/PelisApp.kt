package com.example.pelisproapp.di

import android.app.Application

class PelisApp:Application() {

    override fun onCreate() {
        NetworkProvider.provideRepository()
        /*cuando se abra la aplicacion se inicializa el repo*/
        super.onCreate()
    }
}