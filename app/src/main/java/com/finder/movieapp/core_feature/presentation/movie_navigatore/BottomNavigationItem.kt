package com.finder.movieapp.core_feature.presentation.movie_navigatore

import androidx.annotation.DrawableRes

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)