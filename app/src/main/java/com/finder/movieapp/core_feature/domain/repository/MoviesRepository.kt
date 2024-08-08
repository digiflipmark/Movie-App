package com.finder.movieapp.core_feature.domain.repository

import androidx.paging.PagingData
import com.finder.movieapp.core_feature.data.remote.dto.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

      fun getTrendingMovies(): Flow<PagingData<Result>>
}