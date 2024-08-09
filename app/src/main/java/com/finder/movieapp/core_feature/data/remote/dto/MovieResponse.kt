package com.finder.movieapp.core_feature.data.remote.dto

data class MovieResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
