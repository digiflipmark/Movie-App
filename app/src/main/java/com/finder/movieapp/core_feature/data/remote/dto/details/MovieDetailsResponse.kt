package com.finder.movieapp.core_feature.data.remote.dto.details

import android.icu.text.DecimalFormat
import com.finder.movieapp.core_feature.domain.model.DetailsModel
import com.finder.movieapp.core_feature.presentation.util.Constants

data class MovieDetailsResponse(
    val adult: Boolean?,
    val backdrop_path: String?,
    val belongs_to_collection: BelongsToCollection?,
    val budget: Int?,
    val genres: List<Genre>?,
    val homepage: String?,
    val id: Int?,
    val imdb_id: String?,
    val origin_country: List<String>?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>?,
    val production_countries: List<ProductionCountry>?,
    val release_date: String?,
    val revenue: Int?,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguage>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

fun MovieDetailsResponse.toDomain(): DetailsModel {

    val year = if (release_date.isNullOrEmpty()) 1234 else release_date.take(4).toInt()
    val genres = genres?.map { it.name.orEmpty() } ?: emptyList()
    return DetailsModel(
        id = id ?: 0,
        title = title ?: "",
        backdropPath = backdrop_path ?: "",
        averageVoting = averageVotingConvertor(vote_average ?: 0.0),
        posterPath = poster_path ?: "",
        releaseYear = year,
        durationInMinutes = runtime ?: 0,
        genre = genres[0],
        aboutMovie = overview ?: ""
    )
}

fun DetailsModel?.correctImagePath(): DetailsModel? {
    return this?.copy(
        posterPath = Constants.IMAGES_BASE_PATH + this.posterPath,
        backdropPath = Constants.IMAGES_BASE_PATH + this.backdropPath
    )
}


fun averageVotingConvertor(averageRating: Double): Double {
    val df = DecimalFormat("#.#")
    return df.format(averageRating).toDouble()
}