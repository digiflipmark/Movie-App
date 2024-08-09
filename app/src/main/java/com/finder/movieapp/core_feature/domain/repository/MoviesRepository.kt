package com.finder.movieapp.core_feature.domain.repository

import androidx.paging.PagingData
import com.finder.movieapp.core_feature.data.remote.dto.Result
import com.finder.movieapp.core_feature.domain.util.MoviesGenre
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

      fun getTrendingMovies(): Flow<PagingData<Result>>
      fun getMultipleMovies(moviesGenre: MoviesGenre): Flow<PagingData<Result>>
}