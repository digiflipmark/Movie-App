package com.finder.movieapp.core_feature.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.finder.movieapp.core_feature.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(moviesRepository: MoviesRepository) :
    ViewModel() {

    val trendingMovies = moviesRepository.getTrendingMovies().cachedIn(viewModelScope)
}