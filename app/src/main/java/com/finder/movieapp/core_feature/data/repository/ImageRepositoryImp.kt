package com.finder.movieapp.core_feature.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.finder.movieapp.core_feature.data.remote.ImageApi
import com.finder.movieapp.core_feature.data.remote.dto.ErrorResponse
import com.finder.movieapp.core_feature.domain.repository.ImageRepository
import com.finder.movieapp.core_feature.domain.util.Resource
import com.google.gson.Gson
import okhttp3.ResponseBody
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class ImageRepositoryImp @Inject constructor(private val imageApi: ImageApi) : ImageRepository {

    override suspend fun downloadImage(imagePath: String): Resource<Bitmap> {
        return try {
            val response = imageApi.downloadImage(imagePath)
            val byteArray = response.bytes()
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            Resource.Success(data = bmp)
        } catch (e: HttpException) {
            val errorResponse = parseErrorResponse(e.response()?.errorBody())
            Resource.Error(e, errorResponse)
        } catch (e: IOException) {
            Resource.Error(e, errorResponse = null)
        } catch (e: Exception) {
            Resource.Error(e, errorResponse = null)
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