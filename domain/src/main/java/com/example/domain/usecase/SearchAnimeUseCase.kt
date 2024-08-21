package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.models.Anime
import com.example.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    fun execute(query: String): Flow<PagingData<Anime>> {
        return repository.searchAnime(query)
    }
}