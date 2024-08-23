package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.RequestResult
import com.example.domain.models.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getTopAnime(): Flow<PagingData<Anime>>
    suspend fun getAnimeDetails(id: Int): RequestResult<Anime>
    fun getFavorites(): Flow<List<Anime>>
    suspend fun addToFavorites(anime: Anime)
    suspend fun deleteFromFavorites(id: Int)
    fun containsFavorite(id: Int): Flow<Boolean>
    fun searchAnime(query: String): Flow<PagingData<Anime>>
}