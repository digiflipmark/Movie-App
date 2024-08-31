package com.finder.movieapp.core_feature.presentation.watchlist_screen.componet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.finder.movieapp.R
import com.finder.movieapp.core_feature.domain.model.DetailsModel
import com.finder.movieapp.core_feature.presentation.util.Constants
import com.finder.movieapp.ui.theme.MontserratFont
import com.finder.movieapp.ui.theme.MovieAppTheme
import com.finder.movieapp.ui.theme.Orange
import com.finder.movieapp.ui.theme.PoppinsFont

@Composable
fun WatchListDetailCard(detailsModel: DetailsModel, onClick: (id: Int) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(color = Color.Transparent)
            .clickable { onClick(detailsModel.id) },
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {


        WatchListCard(modifier = Modifier
            .fillMaxHeight()
            .width(95.dp),
            detailsModel.posterPath,
            onClick = { })

        Column {
            Text(
                text = detailsModel.genre,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontFamily = PoppinsFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.White
                ),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(14.dp))

            Column(
                modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
            ) {
                WatchListInfo(
                    text = detailsModel.averageVoting.toString(),
                    textStyle = TextStyle(
                        fontFamily = MontserratFont,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    ),
                    icon = painterResource(id = R.drawable.ic_star),
                    textColor = Orange,
                    iconTint = Orange
                )

                WatchListInfo(
                    text = detailsModel.title,
                    textStyle = TextStyle(
                        fontFamily = MontserratFont,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    ),
                    icon = painterResource(id = R.drawable.ic_ticket)
                )

                WatchListInfo(
                    text = detailsModel.releaseYear.toString(),
                    textStyle = TextStyle(
                        fontFamily = MontserratFont,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    ),
                    icon = painterResource(id = R.drawable.ic_calendar)
                )

                WatchListInfo(
                    text = "${detailsModel.durationInMinutes} ${stringResource(id = R.string.minutes)}",
                    textStyle = TextStyle(
                        fontFamily = MontserratFont,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    ),
                    icon = painterResource(id = R.drawable.ic_clock)
                )

            }
        }


    }
}

@Composable
fun WatchListInfo(
    text: String,
    textStyle: TextStyle,
    textColor: Color = Color.White,
    icon: Painter,
    iconTint: Color = Color.White
) {

    Row {

        Icon(
            painter = icon,
            contentDescription = text,
            modifier = Modifier.size(16.dp),
            tint = iconTint
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = text, style = textStyle, color = textColor, maxLines = 1)
    }
}

@Preview(name = "Light", backgroundColor = 0xFFFFFFFF, showBackground = true)
@Preview(name = "Dark", backgroundColor = 0xFF000000, showBackground = true)
@Composable
private fun WatchListDetailCardPrev() {

    MovieAppTheme {
        WatchListDetailCard(
            detailsModel = DetailsModel(
                id = 1,
                title = "Actions",
                averageVoting = 1.0,
                backdropPath = Constants.IMAGES_BASE_PATH + "/stKGOm8UyhuLPR9sZLjs5AkmncA.jpg",
                posterPath = Constants.IMAGE_BASE_URL + "/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg",
                releaseYear = 2021,
                durationInMinutes = 40,
                genre = "Animation",
                aboutMovie = ""
            ), onClick = {}
        )
    }
}

