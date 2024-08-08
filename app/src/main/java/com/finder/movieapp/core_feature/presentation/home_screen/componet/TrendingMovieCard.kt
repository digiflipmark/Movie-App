package com.finder.movieapp.core_feature.presentation.home_screen.componet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.finder.movieapp.core_feature.data.remote.dto.Result

@Composable
fun TrendingMovieCard(movieItem: Result, onClick: (Result) -> Unit) {

    Box(
        modifier = Modifier
            .width(154.dp)
            .height(226.dp)
    ) {
        MovieCard(
            movieItem = movieItem, modifier = Modifier
                .width(144.dp)
                .height(210.dp)
                .align(Alignment.TopEnd), onClick = { onClick(movieItem) }
        )
    }
}