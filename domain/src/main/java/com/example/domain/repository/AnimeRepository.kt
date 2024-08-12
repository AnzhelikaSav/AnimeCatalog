package com.example.domain.repository

import com.example.domain.models.Anime

interface AnimeRepository {
    suspend fun getTopAnime(): List<Anime>
}