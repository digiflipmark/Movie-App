package com.finder.movieapp.core_feature.domain.model

data class DetailsModel(
    val id: Int,
    val title: String,
    val averageVoting: Double,
    val backdropPath: String,
    val posterPath: String,
    val releaseYear: Int,
    val durationInMinutes: Int?,
    val genre: String,
    val aboutMovie: String
)
