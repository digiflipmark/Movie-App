package com.finder.movieapp.core_feature.data.mapper

import com.finder.movieapp.core_feature.data.local.entites.MovieEntity
import com.finder.movieapp.core_feature.domain.model.DetailsModel


fun DetailsModel.toMovieEntity(): MovieEntity {

    return MovieEntity(
        movieId = id,
        title = title,
        genre = genre,
        releaseYear = releaseYear,
        aboutMovie = aboutMovie,
        rate = averageVoting,
        cover = posterPath, banner = backdropPath, durationInMinutes = durationInMinutes

    )
}

fun MovieEntity.toMovieDetails(): DetailsModel {

    return DetailsModel(
        id = movieId,
        title = title,
        averageVoting = rate,
        backdropPath = banner,
        releaseYear = releaseYear,
        posterPath = cover,
        durationInMinutes = durationInMinutes,
        genre = genre,
        aboutMovie = aboutMovie
    )
}