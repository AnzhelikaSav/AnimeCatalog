package com.example.domain.usecase

import com.example.domain.models.Anime
import com.example.domain.repository.AnimeRepository
import javax.inject.Inject

class GetAnimeDetailsUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend fun execute(id: Int): Anime {
        return repository.getAnimeDetails(id)
    }
}