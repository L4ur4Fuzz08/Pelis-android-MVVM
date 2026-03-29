package com.example.pelisproapp.presentacion.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pelicula(
    val peliculaId: String,
    val peliculaTitle: String,
    val peliculaOverview: String,
    val peliculaPosterPath : String,
    val peliculaFechaLanzamiento: String,
    val peliculaPopularity: Int,
    val peliculaOriginalLanguage: String,
    val peliculaVoteAverage: Double,
    val peliculaBackdrop: String
):Parcelable
