package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.database.dao.FavoritesDao
import com.example.data.mappers.toDatabase
import com.example.data.mappers.toDomain
import com.example.data.network.AnimePagingSource
import com.example.data.network.api.AnimeApi
import com.example.domain.models.Anime
import com.example.domain.repository.AnimeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val PAGE_SIZE = 25

class AnimeRepositoryImpl @Inject constructor(
    private val animeApi: AnimeApi,
    private val pagingSource: AnimePagingSource,
    private val favoritesDao: FavoritesDao,
    private val dispatcher: CoroutineDispatcher
) : AnimeRepository {

    override fun getTopAnimeFlow(): Flow<PagingData<Anime>> = Pager(
        PagingConfig(PAGE_SIZE)
    ) {
        pagingSource
    }.flow
        .map { pagingData ->
            pagingData.map { it.toDomain() }
        }

    override suspend fun getAnimeDetails(id: Int): Anime = withContext(dispatcher) {
        animeApi.getAnime(id).data.toDomain()
    }

    override fun getFavorites(): Flow<List<Anime>> =
        favoritesDao.getAll().map { entities ->
            entities.map { it.toDomain() }
        }
            .flowOn(dispatcher)

    override suspend fun addToFavorites(anime: Anime) = withContext(dispatcher) {
        favoritesDao.add(anime.toDatabase())
    }

    override suspend fun deleteFromFavorites(id: Int) = withContext(dispatcher) {
        favoritesDao.delete(id)
    }

    override fun containsFavorite(id: Int): Flow<Boolean> =
        favoritesDao.get(id).map { it != null }
}