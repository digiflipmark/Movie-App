package com.finder.movieapp.core_feature.presentation.image_viewer_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.finder.movieapp.core_feature.data.worker.DownloadImageWorker
import com.finder.movieapp.core_feature.presentation.util.ImageViewerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val _downloadState = MutableStateFlow(ImageViewerState())
    var downloadState = _downloadState.asStateFlow()

    private val _message = MutableSharedFlow<String>()
    var message = _message.asSharedFlow()


    fun downloadImage(imagePath: String) {
        val downloadRequest =
            OneTimeWorkRequestBuilder<DownloadImageWorker>().setInputData(workDataOf("imagePath" to imagePath))
                .build()
        WorkManager.getInstance(getApplication())
            .beginUniqueWork("imageSaved", ExistingWorkPolicy.APPEND_OR_REPLACE, downloadRequest)
            .enqueue()
        val workerUUID = downloadRequest.id
        observeChanges(workerUUID)
    }

    private fun observeChanges(workerUUID: UUID) {

        viewModelScope.launch {
            WorkManager.getInstance(getApplication()).getWorkInfoByIdLiveData(workerUUID).asFlow()
                .collectLatest {

                    when (it.state.name) {
                        WorkInfo.State.SUCCEEDED.name -> {
                            _downloadState.value = _downloadState.value.copy(
                                loading = false,
                                success = true
                            )
                            viewModelScope.launch {
                                _message.emit("Image saved")
                            }
                        }

                        WorkInfo.State.RUNNING.name -> {
                            _downloadState.value =
                                downloadState.value.copy(loading = true)

                        }

                        WorkInfo.State.FAILED.name -> {
                            _downloadState.value = _downloadState.value.copy(
                                loading = false,
                                success = false
                            )

                            viewModelScope.launch {
                                _message.emit("Couldn't save the image")
                            }
                        }

                    }
                }
        }
    }


}