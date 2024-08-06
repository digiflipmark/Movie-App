package com.finder.movieapp.core_feature.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.finder.movieapp.core_feature.data.remote.MoviesApi
import com.finder.movieapp.core_feature.data.remote.dto.toMovieModel
import com.finder.movieapp.core_feature.domain.model.MovieModel

class TrendingPagingSource(private val moviesApi: MoviesApi) : PagingSource<Int, MovieModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val position = params.key ?: 1
            val response = moviesApi.getTrendingMovies(page = position)
            LoadResult.Page(
                data = response.results.map { it.toMovieModel() },
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.total_pages) null else position + 1
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}