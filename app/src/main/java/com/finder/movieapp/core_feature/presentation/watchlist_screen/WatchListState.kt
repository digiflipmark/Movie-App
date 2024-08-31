package com.finder.movieapp.core_feature.presentation.watchlist_screen

import com.finder.movieapp.core_feature.domain.model.DetailsModel

data class WatchListState(
    val data: List<DetailsModel> = emptyList(),
    val empty: Boolean = false
)
