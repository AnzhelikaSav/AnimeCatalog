package com.example.domain.usecase

import com.example.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckIsFavoriteUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    fun execute(id: Int): Flow<Boolean> {
        return repository.containsFavorite(id)
    }
}