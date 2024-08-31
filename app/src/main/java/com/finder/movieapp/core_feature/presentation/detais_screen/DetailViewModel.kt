package com.finder.movieapp.core_feature.presentation.detais_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.finder.movieapp.core_feature.data.remote.dto.Result
import com.finder.movieapp.core_feature.domain.model.ReviewModel
import com.finder.movieapp.core_feature.domain.repository.MovieDbRepository
import com.finder.movieapp.core_feature.domain.repository.MoviesRepository
import com.finder.movieapp.core_feature.domain.usecase.CastUseCase
import com.finder.movieapp.core_feature.domain.usecase.DetailsUseCase
import com.finder.movieapp.core_feature.domain.usecase.ReviewUseCase
import com.finder.movieapp.core_feature.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: DetailsUseCase,
    private val castUseCase: CastUseCase,
    reviewUseCase: ReviewUseCase,
    private val repository: MovieDbRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailState())
    val detailState= _detailState.asStateFlow()

    var reviewFlow by mutableStateOf<Flow<PagingData<ReviewModel>>>(flowOf())
        private set
    var movieId: Int? = null

    init {
        val movieId = savedStateHandle.get<Int>("movieId") ?: 0
        Log.e("movieId", ": ${movieId}")
        this.movieId = movieId
        getDetails(movieId)
        getMovieCast(movieId)
        reviewFlow = reviewUseCase.invoke(movieId).cachedIn(viewModelScope)
    }

    private fun getDetails(id: Int) {
        useCase.invoke(id).onEach {
            when (it) {
                is Resource.Error -> {
                    if (it.isNetworkError) {
                        _detailState.value = _detailState.value.copy(
                            isLoading = false,
                            error = "Please check your internet connection",
                            data = null
                        )
                    } else {
                        _detailState.value = _detailState.value.copy(
                            isLoading = false,
                            error = it.errorResponse?.statusMessage ?: "Something went wrong",
                            data = null
                        )
                    }
                }

                Resource.Loading -> {

                    _detailState.value = _detailState.value.copy(
                        isLoading = true, error = "", data = null
                    )
                }


                is Resource.Success -> _detailState.value = _detailState.value.copy(
                    isLoading = false, error = "", data = it.data
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun getMovieCast(id: Int) {
        castUseCase(id).onEach {
            when (it) {
                is Resource.Error -> {
                    if (it.isNetworkError) {
                        _detailState.value = _detailState.value.copy(
                            castLoading = false,
                            castError = "Please check your internet connection",
                            castData = null
                        )
                    } else {
                        _detailState.value = _detailState.value.copy(
                            castLoading = false,
                            castError = it.errorResponse?.statusMessage ?: "Something went wrong",
                            castData = null
                        )
                    }
                }

                Resource.Loading -> {

                    _detailState.value = _detailState.value.copy(
                        castLoading = true, castError = "", castData = null
                    )
                }


                is Resource.Success -> _detailState.value = _detailState.value.copy(
                    castLoading = false, castError = "", castData = it.data
                )
            }
        }.launchIn(viewModelScope)
    }

    fun insertDeleteMovie() {
        viewModelScope.launch {
            movieId?.let { movieId ->
                val moviedatabase = repository.getMovieDbById(movieId)
                if (moviedatabase == null) {
                    detailState.value.data?.let { movie ->
                        repository.inertMovie(movie)
                        _detailState.value = detailState.value.copy(isSaved = true)
                    }
                } else {
                    repository.deleteMovieId(movieId)
                    _detailState.value = detailState.value.copy(isSaved = false)
                }
            }
        }
    }

}