package com.finder.movieapp.core_feature.presentation.util.componets

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
fun TextIcon(
    modifier: Modifier = Modifier,
    text: String,
    icon: Painter,
    space: Dp = 4.dp,
    textStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = MontserratFont,
        color = Color.White
    ),
    color: Color? = null,
) {

    Row {

        Icon(
            painter = icon,
            contentDescription = "",
            tint = color ?: Color.White,
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(space))

        Text(
            text = text, style = textStyle,
            color = color ?: Color.White,
            maxLines = 1
        )
    }
}