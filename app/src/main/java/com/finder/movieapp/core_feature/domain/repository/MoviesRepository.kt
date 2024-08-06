package com.finder.movieapp.core_feature.domain.repository

import androidx.paging.PagingData
import com.finder.movieapp.core_feature.data.remote.dto.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getTrendingMovies(): Flow<PagingData<MovieResponse>>
}