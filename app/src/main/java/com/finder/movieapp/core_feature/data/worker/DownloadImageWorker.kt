package com.finder.movieapp.core_feature.data.worker

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.finder.movieapp.core_feature.domain.repository.ImageRepository
import com.finder.movieapp.core_feature.domain.util.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class DownloadImageWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val imageRepository: ImageRepository
) : CoroutineWorker(appContext, workerParameters) {


    override suspend fun doWork(): Result {
        setProgress(workDataOf("loading" to true))
        val imagePath: String? = workerParameters.inputData.getString("imagePath")
        Log.e("TAG", "DownloadImageWorker: ${imagePath}")
        return withContext(Dispatchers.IO) {
            try {
                if (imagePath != null) {
                    val result = imageRepository.downloadImage(imagePath)
                    when (result) {
                        is Resource.Error -> {

                            Result.failure()
                        }

                        is Resource.Success -> {
                            val data = result.data
                            saveImage(data, imagePath)
                            setProgress(workDataOf("loading" to false))
                            Result.success()
                        }

                        else -> throw Exception("Unknown result")
                    }
                } else {
                    throw Exception("imagePath can't be null")
                }
            } catch (e: Exception) {
                setProgress(workDataOf("loading" to false))
                e.printStackTrace()
                Result.failure(workDataOf("error" to e.message))
            }
        }

    }

    private fun saveImage(bmp: Bitmap, imagePath: String) {

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.WIDTH, bmp.width)
            put(MediaStore.Images.Media.HEIGHT, bmp.height)
            put(MediaStore.Images.Media.DISPLAY_NAME, imagePath)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        val imagesUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
        val imageUri = appContext.contentResolver.insert(imagesUri, contentValues)
        imageUri?.let {
            appContext.contentResolver.openOutputStream(it).use { stream ->
                if (!bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream!!))
                    throw Exception("Couldn't save the image")
            }
        }
    }
}

