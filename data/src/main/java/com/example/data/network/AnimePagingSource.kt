package com.example.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.network.api.AnimeApi
import com.example.data.network.models.AnimeNetwork
import javax.inject.Inject

class AnimePagingSource @Inject constructor(
    private val animeApi: AnimeApi
) : PagingSource<Int, AnimeNetwork>()  {

    override fun getRefreshKey(state: PagingState<Int, AnimeNetwork>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeNetwork> {
        return try {
            val page = params.key ?: STARTING_KEY
            val response = animeApi.getTopAnime(page)

            val prevPage = if (page == STARTING_KEY) null else page - 1
            val nextPage = if (response.pagination.hasNextPage) page + 1 else null

            LoadResult.Page(response.data, prevPage, nextPage)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val STARTING_KEY = 1
    }
}