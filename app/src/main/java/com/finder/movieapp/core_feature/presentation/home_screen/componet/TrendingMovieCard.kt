package com.finder.movieapp.core_feature.presentation.home_screen.componet

import android.os.Build
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.finder.movieapp.core_feature.data.remote.dto.Result
import com.finder.movieapp.ui.theme.BlackPurple
import com.finder.movieapp.ui.theme.BlueLight

@Composable
fun TrendingMovieCard(
    number: Int,
    movieItem: Result, onClick: (Result) -> Unit) {
    val context = LocalContext.current
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

        val size =
            with(LocalDensity.current) {
                96.dp.toPx()
            }


        val textPaintStroke = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            style = android.graphics.Paint.Style.STROKE
            textSize = size
            color = BlueLight.toArgb()
            strokeWidth = 1f
            strokeMiter = 10f
            strokeJoin = android.graphics.Paint.Join.ROUND

        }

        val textPaint = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            style = android.graphics.Paint.Style.FILL
            textSize = size
            color = BlackPurple.toArgb()

        }

        Canvas(modifier = Modifier.matchParentSize()) {
            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    number.toString(),
                    0f,
                    this.size.height,
                    textPaint
                )

                it.nativeCanvas.drawText(
                    number.toString(),
                    0f,
                    this.size.height,
                    textPaintStroke
                )
            }
        }
    }
}