package com.finder.movieapp.core_feature.data.repository

import com.finder.movieapp.core_feature.data.local.MovieDao
import com.finder.movieapp.core_feature.data.mapper.toMovieDetails
import com.finder.movieapp.core_feature.data.mapper.toMovieEntity
import com.finder.movieapp.core_feature.domain.model.DetailsModel
import com.finder.movieapp.core_feature.domain.repository.MovieDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieDbRepoImp @Inject constructor(private val dao: MovieDao) : MovieDbRepository {

    override suspend fun inertMovie(detailsModel: DetailsModel) {
        dao.insertUser(detailsModel.toMovieEntity())
    }

    override fun getSavedMovies(): Flow<List<DetailsModel>> {
        return dao.getAllMovies().map {
            it.map { it.toMovieDetails() }
        }
    }

    override suspend fun deleteMovieId(id: Int) {
        dao.deleteMovieById(id)
    }

    override suspend fun getMovieDbById(id: Int): DetailsModel? {
        return dao.getMovieById(id)?.toMovieDetails()
    }
}