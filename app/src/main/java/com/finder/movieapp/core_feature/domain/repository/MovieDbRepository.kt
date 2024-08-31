package com.finder.movieapp.core_feature.domain.repository

import com.finder.movieapp.core_feature.domain.model.DetailsModel
import kotlinx.coroutines.flow.Flow

interface MovieDbRepository {

    suspend fun inertMovie(detailsModel: DetailsModel)
    fun getSavedMovies(): Flow<List<DetailsModel>>
    suspend fun deleteMovieId(id: Int)
    suspend fun getMovieDbById(id: Int): DetailsModel?
}