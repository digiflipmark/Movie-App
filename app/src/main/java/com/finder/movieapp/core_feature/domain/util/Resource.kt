package com.finder.movieapp.core_feature.domain.util

import com.finder.movieapp.core_feature.data.remote.dto.ErrorResponse
import okio.IOException

sealed class Resource<out T> {

    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(
        val exception: Throwable,
        val errorResponse: ErrorResponse? = null,
        val isNetworkError: Boolean = exception is IOException
    ) :
        Resource<Nothing>()

    data object Loading : Resource<Nothing>()
}