package com.example.data.repository

import com.example.data.mappers.toDomain
import com.example.data.network.api.AnimeApi
import com.example.domain.models.Anime
import com.example.domain.repository.AnimeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeApi: AnimeApi,
    private val dispatcher: CoroutineDispatcher
): AnimeRepository {
    override suspend fun getTopAnime(): List<Anime> = withContext(dispatcher) {
        animeApi.getTopAnime().data.map { it.toDomain() }
    }

    override suspend fun getAnimeDetails(id: Int): Anime = withContext(dispatcher) {
        animeApi.getAnime(id).data.toDomain()
    }
}