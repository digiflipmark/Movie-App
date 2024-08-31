package com.finder.movieapp.core_feature.presentation.util

data class ImageViewerState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)
