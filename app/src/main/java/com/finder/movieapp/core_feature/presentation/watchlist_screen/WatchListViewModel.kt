package com.finder.movieapp.core_feature.presentation.watchlist_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finder.movieapp.core_feature.domain.repository.MovieDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(private val movieDbRepository: MovieDbRepository) :
    ViewModel() {

    private val _watchList = MutableStateFlow(WatchListState())
    val watchList = _watchList.asStateFlow()

    init {
        viewModelScope.launch {
            movieDbRepository.getSavedMovies().collectLatest {
                _watchList.value = _watchList.value.copy(data = it, empty = it.isEmpty())
            }
        }
    }

}