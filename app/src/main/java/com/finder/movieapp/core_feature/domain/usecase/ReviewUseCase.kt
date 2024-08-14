package com.finder.movieapp.core_feature.domain.usecase

import androidx.paging.PagingData
import com.finder.movieapp.core_feature.data.remote.dto.ErrorResponse
import com.finder.movieapp.core_feature.data.remote.dto.cast.toDomain
import com.finder.movieapp.core_feature.domain.model.ReviewModel
import com.finder.movieapp.core_feature.domain.repository.MoviesRepository
import com.finder.movieapp.core_feature.domain.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class ReviewUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    operator fun invoke(movieId: Int): Flow<PagingData<ReviewModel>> {
        return moviesRepository.getMovieReview(movieId)
    }
}