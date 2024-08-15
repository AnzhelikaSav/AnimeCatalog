package com.example.domain.models

data class Anime(
    val id: Int,
    val imageUrl: String,
    val largeImageUrl: String,
    val title: String,
    val score: Double,
    val year: Int,
    val genres: List<String>,
    val synopsis: String
)