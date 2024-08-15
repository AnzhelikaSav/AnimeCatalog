package com.example.domain.usecase

import com.example.domain.models.Anime
import com.example.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    fun execute(): Flow<List<Anime>> {
        return repository.getFavorites()
    }
}