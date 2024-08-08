package com.finder.movieapp.core_feature.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.finder.movieapp.core_feature.data.remote.MoviesApi
import com.finder.movieapp.core_feature.data.remote.dto.Result

class TrendingPagingSource(private val moviesApi: MoviesApi) :
    PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        return try {
            val page = params.key ?: 1
            val response = moviesApi.getTrendingMovies(page = page)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == response.total_results) null else page + 1
            )


        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}