package com.finder.movieapp.core_feature.data.remote.dto.cast

data class CastDto(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)