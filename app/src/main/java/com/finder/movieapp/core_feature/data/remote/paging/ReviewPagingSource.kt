package com.finder.movieapp.core_feature.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.finder.movieapp.core_feature.data.remote.MoviesApi
import com.finder.movieapp.core_feature.data.remote.dto.Result
import com.finder.movieapp.core_feature.data.remote.dto.review.toDomain
import com.finder.movieapp.core_feature.domain.model.ReviewModel
import okio.IOException
import retrofit2.HttpException
import java.net.UnknownHostException

class ReviewPagingSource(private val moviesApi: MoviesApi, private val movieId: Int) :
    PagingSource<Int, ReviewModel>() {

    override fun getRefreshKey(state: PagingState<Int, ReviewModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewModel> {

        return try {
            val page = params.key ?: 1
            val response = moviesApi.getMovieReviews(movieId = movieId, page = page)
            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == response.total_pages) null else page + 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception: UnknownHostException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}