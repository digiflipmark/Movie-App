package com.finder.movieapp.core_feature.presentation.util.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.finder.movieapp.R
import com.finder.movieapp.ui.theme.MontserratFont

@Composable
fun ScreenToolBar(
    modifier: Modifier = Modifier,
    title: String,
    navigateBackRequest: () -> Unit,
    iconWidth: Dp = 24.dp,
    icon: Painter? = null,
    iconClick: (() -> Unit)? = null,
) {

    Box(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = navigateBackRequest) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp), tint = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = title,
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = MontserratFont,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            if (icon != null) {
                IconButton(
                    onClick = { iconClick?.invoke() },
                    modifier = Modifier
                        .width(iconWidth)
                        .fillMaxHeight(),
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.White
                    )
                }
            }

        }


    }
}

@Preview(showBackground = true)
@Composable
private fun Display() {

    ScreenToolBar(
        title = "Watch List",
        navigateBackRequest = { /*TODO*/ },
        modifier = Modifier
            .background(color = Color.Black)
            .height(50.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    )
}