package com.finder.movieapp.core_feature.data.remote.dto.review

import com.finder.movieapp.core_feature.domain.model.ReviewModel
import com.finder.movieapp.core_feature.presentation.util.Constants

data class Result(
    val author: String?,
    val author_details: AuthorDetails,
    val content: String?,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)

fun Result.toDomain(): ReviewModel {
    return ReviewModel(
        author = author ?: "N/A",
        rating = author_details.rating ?: 0.0,
        avatarPath = author_details.avatar_path.correctImagePath(),
        content = content ?: "N/A"
    )
}

fun String?.correctImagePath(): String {
    return Constants.IMAGES_BASE_PATH + this
}