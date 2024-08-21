package com.example.data.network.paging

import androidx.paging.Pager
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.network.models.AnimeListNetwork
import com.example.data.network.models.AnimeNetwork

class BasePagingSource(
    private val request: suspend (Int) -> AnimeListNetwork
): PagingSource<Int, AnimeNetwork>() {
    override fun getRefreshKey(state: PagingState<Int, AnimeNetwork>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeNetwork> {
        return try {
            val page = params.key ?: PagingConfig.STARTING_KEY
            val response = request.invoke(page)

            val prevPage = if (page == PagingConfig.STARTING_KEY) null else page - 1
            val nextPage = if (response.pagination.hasNextPage) page + 1 else null

            LoadResult.Page(response.data, prevPage, nextPage)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

fun createPager(
    pageSize: Int = PagingConfig.PAGE_SIZE,
    request: suspend (Int) -> AnimeListNetwork
): Pager<Int, AnimeNetwork> = Pager(
    config = androidx.paging.PagingConfig(pageSize),
    pagingSourceFactory = { BasePagingSource(request) }
)