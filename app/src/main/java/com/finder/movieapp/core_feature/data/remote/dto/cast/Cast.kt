package com.finder.movieapp.core_feature.data.remote.dto.cast

import com.finder.movieapp.core_feature.domain.model.CastModel

data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String?,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?
)

fun Cast.toDomain(): CastModel {
    return CastModel(profile = profile_path ?: "", name = name ?: "N/A")
}