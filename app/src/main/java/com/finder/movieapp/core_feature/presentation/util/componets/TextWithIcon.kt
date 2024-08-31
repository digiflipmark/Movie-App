package com.finder.movieapp.core_feature.presentation.util.componets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.finder.movieapp.ui.theme.MontserratFont

@Composable
fun TextWithIcon(
    text: String, painter: Painter,
    color: Color? = null,
    space: Dp = 4.dp,
    textStyle: TextStyle = TextStyle(
        fontFamily = MontserratFont,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp, color = Color.White
    )
) {

    Row {
        Icon(
            painter = painter,
            contentDescription = "",
            modifier = Modifier.size(18.dp), tint = color ?: Color.White
        )
        Spacer(modifier = Modifier.width(space))
        Text(
            text = text,
            style = textStyle,
            color = color ?: Color.White,
            maxLines = 1
        )
    }
}

