package com.finder.movieapp.core_feature.presentation.home_screen

import com.finder.movieapp.core_feature.domain.util.MoviesGenre

data class TabLayoutSectionState(

    val selectedTab: MoviesGenre = MoviesGenre.NOW_PLAYING,
    val selectedIndex: Int = 0,
)