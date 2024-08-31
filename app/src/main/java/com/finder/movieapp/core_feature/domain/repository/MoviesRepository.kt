package com.finder.movieapp.core_feature.domain.repository

import androidx.paging.PagingData
import com.finder.movieapp.core_feature.data.remote.dto.Result
import com.finder.movieapp.core_feature.data.remote.dto.cast.CastDto
import com.finder.movieapp.core_feature.data.remote.dto.details.MovieDetailsResponse
import com.finder.movieapp.core_feature.data.remote.dto.review.ReviewDto
import com.finder.movieapp.core_feature.domain.model.DetailsModel
import com.finder.movieapp.core_feature.domain.model.ReviewModel
import com.finder.movieapp.core_feature.domain.util.MoviesGenre
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getTrendingMovies(): Flow<PagingData<Result>>
    fun getMultipleMovies(moviesGenre: MoviesGenre): Flow<PagingData<Result>>
    suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse
    suspend fun getMovieCast(movieId: Int): CastDto

    fun getMovieReview(movieId: Int): Flow<PagingData<ReviewModel>>

}