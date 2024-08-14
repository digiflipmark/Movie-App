package com.finder.movieapp.core_feature.domain.usecase

import com.finder.movieapp.core_feature.data.remote.dto.ErrorResponse
import com.finder.movieapp.core_feature.data.remote.dto.cast.toDomain
import com.finder.movieapp.core_feature.domain.model.CastModel
import com.finder.movieapp.core_feature.domain.repository.MoviesRepository
import com.finder.movieapp.core_feature.domain.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CastUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    operator fun invoke(movieId: Int): Flow<Resource<List<CastModel>>> {
        return flow {
            try {
                emit(Resource.Loading)
                val castResponse =
                    moviesRepository.getMovieCast(movieId = movieId).cast.map { it.toDomain() }
                emit(Resource.Success(castResponse))
            } catch (e: HttpException) {
                val errorResponse = parseErrorResponse(e.response()?.errorBody())
                emit(Resource.Error(e, errorResponse))
            } catch (e: IOException) {
                emit(Resource.Error(e, errorResponse = null))
            } catch (e: Exception) {
                emit(Resource.Error(e, errorResponse = null))
            }
        }
    }
}

private fun parseErrorResponse(errorBody: ResponseBody?): ErrorResponse? {
    return errorBody?.let {
        try {
            Gson().fromJson(it.string(), ErrorResponse::class.java)
        } catch (e: Exception) {
            null
        }
    }
}