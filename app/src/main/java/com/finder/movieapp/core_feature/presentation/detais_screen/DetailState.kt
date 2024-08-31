package com.finder.movieapp.core_feature.presentation.detais_screen

import com.finder.movieapp.core_feature.domain.model.CastModel
import com.finder.movieapp.core_feature.domain.model.DetailsModel

data class DetailState(
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val error: String = "",
    val data: DetailsModel? = null,
    val castLoading: Boolean = false,
    val castError: String = "",
    val castData: List<CastModel>? = emptyList(),
)
