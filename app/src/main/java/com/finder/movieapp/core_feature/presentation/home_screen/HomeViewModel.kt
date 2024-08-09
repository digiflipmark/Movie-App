package com.finder.movieapp.core_feature.presentation.home_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.finder.movieapp.core_feature.data.remote.dto.Result
import com.finder.movieapp.core_feature.domain.repository.MoviesRepository
import com.finder.movieapp.core_feature.domain.util.MoviesGenre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {


    private var _tabLayoutMoviesState = MutableStateFlow(TabLayoutSectionState())
    val tabLayoutMoviesState: StateFlow<TabLayoutSectionState> = _tabLayoutMoviesState

    val trendingMovies = moviesRepository.getTrendingMovies().cachedIn(viewModelScope)

    var pageFlow by mutableStateOf<Flow<PagingData<Result>>>(flowOf())
        private set

    init {

        getMovies(moviesGenre = MoviesGenre.NOW_PLAYING)
    }

    @SuppressLint("CheckResult")
    fun getMovies(moviesGenre: MoviesGenre) {

        pageFlow = moviesRepository.getMultipleMovies(moviesGenre)
            .cachedIn(viewModelScope)
    }

    fun changeMoviesTab(genreTab: MoviesGenre) {
        when (genreTab) {
            MoviesGenre.NOW_PLAYING -> {
                _tabLayoutMoviesState.value = _tabLayoutMoviesState.value.copy(
                    selectedTab = MoviesGenre.NOW_PLAYING,
                    selectedIndex = 0
                )

                getMovies(moviesGenre = MoviesGenre.NOW_PLAYING)
            }

            MoviesGenre.POPULAR -> {
                _tabLayoutMoviesState.value = _tabLayoutMoviesState.value.copy(
                    selectedTab = MoviesGenre.POPULAR,
                    selectedIndex = 3
                )
                getMovies(MoviesGenre.POPULAR)

            }

            MoviesGenre.UPCOMING -> {
                _tabLayoutMoviesState.value = _tabLayoutMoviesState.value.copy(
                    selectedTab = MoviesGenre.UPCOMING,
                    selectedIndex = 1
                )
                getMovies(MoviesGenre.UPCOMING)

            }

            MoviesGenre.TOP_RATED -> {
                _tabLayoutMoviesState.value = _tabLayoutMoviesState.value.copy(
                    selectedTab = MoviesGenre.TOP_RATED,
                    selectedIndex = 2
                )
                getMovies(MoviesGenre.TOP_RATED)

            }

        }

    }
}