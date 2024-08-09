package com.finder.movieapp.core_feature.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.finder.movieapp.core_feature.data.remote.MoviesApi
import com.finder.movieapp.core_feature.data.remote.dto.Result
import com.finder.movieapp.core_feature.data.remote.paging.MultiplePagingSource
import com.finder.movieapp.core_feature.data.remote.paging.TrendingPagingSource
import com.finder.movieapp.core_feature.domain.repository.MoviesRepository
import com.finder.movieapp.core_feature.domain.util.MoviesGenre
import com.finder.movieapp.core_feature.presentation.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(private val moviesApi: MoviesApi) : MoviesRepository {

    override fun getTrendingMovies(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                TrendingPagingSource(moviesApi = moviesApi)
            }).flow.map { it.map { it.copy(poster_path = Constants.IMAGES_BASE_PATH + it.poster_path) } }
    }

    override fun getMultipleMovies(moviesGenre: MoviesGenre): Flow<PagingData<Result>> {
        Log.e("TAG", "getMultipleMovies: ${moviesGenre.name}", )
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MultiplePagingSource(moviesApi = moviesApi, moviesGenre = moviesGenre)
            }).flow.map { it.map { it.copy(poster_path = Constants.IMAGES_BASE_PATH + it.poster_path) } }
    }


}