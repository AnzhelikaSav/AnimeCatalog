package com.example.domain.usecase

import com.example.domain.repository.AnimeRepository
import javax.inject.Inject

class DeleteFromFavoritesUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend fun execute(id: Int) {
        repository.deleteFromFavorites(id)
    }
}