package com.finder.movieapp.core_feature.data.remote.dto

import com.finder.movieapp.core_feature.domain.model.MovieModel

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun Result.toMovieModel(): MovieModel {
    return MovieModel(id = id, coverPath = poster_path ?: "")
}