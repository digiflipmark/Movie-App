package com.finder.movieapp.core_feature.domain.model

data class ReviewModel(
    val author: String,
    val rating: Double,
    val avatarPath: String,
    val content: String,
)
