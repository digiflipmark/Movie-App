package com.finder.movieapp.core_feature.presentation.home_screen.componet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.finder.movieapp.core_feature.data.remote.dto.Result
import com.finder.movieapp.core_feature.presentation.util.ImageLoadingState
import com.finder.movieapp.core_feature.presentation.util.ShimmerCard

@Composable
fun MovieCard(modifier: Modifier = Modifier, movieItem: Result, onClick: (Result) -> Unit) {

    var isImageLoadingState by remember {
        mutableStateOf(ImageLoadingState.LOADING)
    }

    val context = LocalContext.current
    val imageRequest =
        ImageRequest.Builder(context = context).data(movieItem.poster_path).crossfade(true).build()

    Card(modifier = modifier.clickable { onClick(movieItem) }, shape = MaterialTheme.shapes.large) {

        AsyncImage(model = imageRequest,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(),
            onLoading = {
                isImageLoadingState = ImageLoadingState.LOADING
            }, onSuccess = {
                isImageLoadingState = ImageLoadingState.SUCCESS
            }, onError = {
                isImageLoadingState = ImageLoadingState.ERROR
            }
        )
        if (isImageLoadingState == ImageLoadingState.LOADING) {
            ShimmerCard(targetValue = 2000f, modifier = Modifier.fillMaxSize())
        }
        if (isImageLoadingState == ImageLoadingState.ERROR) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
            )
        }
    }
}

/*@Preview
@Composable
private fun DisplayMovieCard() {

    MovieAppTheme {
        MovieCard(
            modifier = Modifier
                .width(139.dp)
                .height(210.dp)
        )
    }
}*/
