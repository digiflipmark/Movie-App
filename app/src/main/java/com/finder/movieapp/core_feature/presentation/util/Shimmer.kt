package com.finder.movieapp.core_feature.presentation.util

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finder.movieapp.ui.theme.Gray600


@Composable
fun ShimmerCard(
    targetValue: Float = 1000f,
    modifier: Modifier = Modifier
) {

    val shimmerColors = listOf(
        MaterialTheme.colorScheme.surface,
        Gray600,
        MaterialTheme.colorScheme.surface,
    )

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val endValue = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = targetValue,
        animationSpec = InfiniteRepeatableSpec(
            tween(1000),
            RepeatMode.Reverse
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = endValue.value, y = endValue.value)
    )

    Spacer(modifier
        .background(brush))

}

@Composable
fun MovieDetailsShimmerCard(
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(120.dp)
        .background(Color.Transparent),
        horizontalArrangement = Arrangement.spacedBy(12.dp)) {

        Box(modifier = Modifier
            .fillMaxHeight()
            .width(95.dp)) {
            ShimmerCard(modifier = Modifier.fillMaxSize())
        }

        Column {
            ShimmerCard(modifier = Modifier.fillMaxWidth(0.75f).height(15.dp))
            Spacer(modifier = Modifier.height(25.dp))
            Column(modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween) {
                ShimmerCard(modifier = Modifier.fillMaxWidth(0.35f).height(10.dp))
                ShimmerCard(modifier = Modifier.fillMaxWidth(0.65f).height(10.dp))
                ShimmerCard(modifier = Modifier.fillMaxWidth(0.2f).height(10.dp))
                ShimmerCard(modifier = Modifier.fillMaxWidth(0.4f).height(10.dp))
            }

        }
    }
}

