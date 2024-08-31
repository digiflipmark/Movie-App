package com.finder.movieapp.core_feature.presentation.watchlist_screen.componet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.finder.movieapp.core_feature.presentation.util.ImageLoadingState
import com.finder.movieapp.core_feature.presentation.util.shimmerCard

@Composable
fun WatchListCard(modifier: Modifier = Modifier, posterPath: String, onClick: () -> Unit) {

    var isImageLoadingState by remember {
        mutableStateOf(ImageLoadingState.LOADING)
    }
    val showShimmer = remember { mutableStateOf(true) }
    val context = LocalContext.current
    val imageRequest =
        ImageRequest.Builder(context = context).data(posterPath).crossfade(true).build()

    Card(
        modifier = modifier.clickable { onClick() },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {

        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .background(
                    shimmerCard(
                        targetValue = 1300f,
                        showShimmer = showShimmer.value
                    )
                )
                .fillMaxSize(),
            onLoading = {
                isImageLoadingState = ImageLoadingState.LOADING
            },
            onSuccess = {
                showShimmer.value = false
                isImageLoadingState = ImageLoadingState.SUCCESS
            },
            onError = {
                showShimmer.value = false
                isImageLoadingState = ImageLoadingState.ERROR
            }
        )

        if (isImageLoadingState == ImageLoadingState.ERROR) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
            )
        }
    }
}