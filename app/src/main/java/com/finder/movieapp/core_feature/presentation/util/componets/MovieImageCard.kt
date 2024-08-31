package com.finder.movieapp.core_feature.presentation.util.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.finder.movieapp.core_feature.presentation.util.shimmerCard
import com.finder.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieImageCard(modifier: Modifier = Modifier, imageUrl: String, shapes: Shape) {

    val context = LocalContext.current
    val showShimmer = remember { mutableStateOf(true) }
    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl).crossfade(200)
        .build()


    Card(
        modifier = modifier,
        shape = shapes,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {

        AsyncImage(
            model = imageRequest,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .background(
                    shimmerCard(
                        targetValue = 1300f,
                        showShimmer = showShimmer.value
                    )
                )
                .fillMaxSize(),
            onLoading = {},
            onError = { showShimmer.value = false },
            onSuccess = { showShimmer.value = false }
        )
    }

}

@Preview
@Composable
fun Display(modifier: Modifier = Modifier) {
    MovieAppTheme {
        MovieImageCard(
            modifier = Modifier
                .height(210.dp)
                .fillMaxWidth(),
            imageUrl = "",
            shapes = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
        )
    }
}