package com.finder.movieapp.core_feature.presentation.watchlist_screen.componet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.finder.movieapp.R
import com.finder.movieapp.ui.theme.Gray500
import com.finder.movieapp.ui.theme.MontserratFont
import com.finder.movieapp.ui.theme.MovieAppTheme

@Composable
fun NoResultScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.empty_box),
                contentDescription = null,
                modifier = Modifier.size(76.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.no_movie_title), style = TextStyle(
                    fontFamily = MontserratFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = stringResource(id = R.string.no_movie_description), style = TextStyle(
                    fontFamily = MontserratFont,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Gray500
                )
            )

        }

    }
}

@Preview
@Composable
private fun NoResultPeview() {
    MovieAppTheme {
        Box {
            NoResultScreen(modifier = Modifier.align(Alignment.Center))
        }

    }
}