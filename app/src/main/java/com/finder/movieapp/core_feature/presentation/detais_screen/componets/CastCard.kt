package com.finder.movieapp.core_feature.presentation.detais_screen.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.finder.movieapp.R
import com.finder.movieapp.core_feature.domain.model.CastModel
import com.finder.movieapp.core_feature.presentation.util.shimmerCard

@Composable
fun CastCard(castModel: CastModel) {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(castModel.profile).crossfade(200)
        .build()
    val showShimmer = remember { mutableStateOf(true) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        ) {

            AsyncImage(
                model = imageRequest,
                modifier = Modifier
                    .background(
                        shimmerCard(
                            showShimmer = showShimmer.value,
                            targetValue = 1300f
                        )
                    )
                    .fillMaxSize(),
                contentDescription = "",
                error = painterResource(id = R.drawable.man_review),
                contentScale = ContentScale.Crop,
                onLoading = {},
                onSuccess = { showShimmer.value = false },
                onError = { showShimmer.value = false }
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = castModel.name,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview
@Composable
private fun Disply() {
    CastCard(
        castModel = CastModel(
            profile = "https://image.tmdb.org/t/p/w500//bTEFpaWd7A6AZVWOqKKBWzKEUe8.jpg",
            name = "Mark Wahlberg"
        )
    )
}