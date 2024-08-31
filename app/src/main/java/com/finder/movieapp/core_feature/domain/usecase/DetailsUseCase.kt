package com.finder.movieapp.core_feature.domain.usecase

import com.finder.movieapp.core_feature.data.remote.dto.ErrorResponse
import com.finder.movieapp.core_feature.data.remote.dto.details.correctImagePath
import com.finder.movieapp.core_feature.data.remote.dto.details.toDomain
import com.finder.movieapp.core_feature.domain.model.DetailsModel
import com.finder.movieapp.core_feature.domain.repository.MoviesRepository
import com.finder.movieapp.core_feature.domain.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class DetailsUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    operator fun invoke(id: Int): Flow<Resource<DetailsModel>> {
        return flow {
            emit(Resource.Loading)
            try {
                val movie = moviesRepository.getMovieDetails(id).toDomain().correctImagePath()

                if (movie != null) {
                    emit(Resource.Success(movie))
                }

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