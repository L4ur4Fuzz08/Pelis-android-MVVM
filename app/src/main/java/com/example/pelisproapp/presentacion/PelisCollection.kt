package com.example.pelisproapp.presentacion

data class PelisCollection<T1, T2, T3>(
    val upcoming :T1,
    val topRated:T2,
    val popular :T3
)
