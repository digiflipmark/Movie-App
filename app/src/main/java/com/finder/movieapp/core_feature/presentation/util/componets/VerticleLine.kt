package com.finder.movieapp.core_feature.presentation.util.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.finder.movieapp.ui.theme.Gray500

@Composable
fun VerticaLine(modifier: Modifier = Modifier, height: Dp = 16.dp) {

    Box(
        modifier = modifier
            .width(1.dp)
            .height(height)
            .background(color = Gray500)
    )
}


