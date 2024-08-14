package com.finder.movieapp.core_feature.presentation.detais_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.finder.movieapp.R
import com.finder.movieapp.core_feature.domain.model.DetailsModel
import com.finder.movieapp.core_feature.presentation.util.componets.MovieImageCard
import com.finder.movieapp.core_feature.presentation.util.componets.ScreenToolBar
import com.finder.movieapp.core_feature.presentation.util.componets.TextIcon
import com.finder.movieapp.ui.theme.Black800
import com.finder.movieapp.ui.theme.MovieAppTheme
import com.finder.movieapp.ui.theme.Orange

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navigateBackRequest: () -> Unit,
    detailViewModel: DetailViewModel = hiltViewModel()
) {

    val state = detailViewModel.detailState.value

    Column {

        Spacer(modifier = Modifier.height(20.dp))

        ScreenToolBar(
            title = stringResource(id = R.string.detail),
            navigateBackRequest = navigateBackRequest,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 22.dp)
        )

        if (state.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp), color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        state.data?.let {
            Log.e("TAG", "backdropPath: ${it.backdropPath}")
        }
        BackdropPosterTitleSection(state.data)
        Spacer(modifier = Modifier.height(20.dp))
    }

}

@Composable
fun BackdropPosterTitleSection(data: DetailsModel?) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(271.dp)
    ) {

        data?.let {
            MovieBackDrop(modifier = Modifier.height(210.dp), it.backdropPath, it.averageVoting)


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .padding(start = 28.dp, end = 28.dp), verticalAlignment = Alignment.Bottom
            ) {

                MovieImageCard(
                    imageUrl = it.posterPath,
                    modifier = Modifier
                        .height(140.dp)
                        .width(95.dp),
                    shapes = RoundedCornerShape(16.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                //For title
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.headlineLarge,
                    maxLines = 2,
                )
            }
        }

    }
}

@Composable
fun MovieBackDrop(modifier: Modifier = Modifier, backDropPath: String, averageRating: Double) {

    Box(
        modifier = modifier.clip(
            shape = RoundedCornerShape(
                bottomStart = 16.dp, bottomEnd = 16.dp
            )
        )
    ) {

        MovieImageCard(
            imageUrl = backDropPath,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp),
            shapes = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
        )

       /* MovieRating(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(end = 11.dp, bottom = 9.dp)
        )*/
    }
}

/*@Composable
fun MovieRating(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .width(54.dp)
            .height(24.dp), contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(Black800)
                .alpha(0.32f)
        )
        TextIcon(text = "2.0", icon = painterResource(id = R.drawable.ic_star), color = Orange)

    }
}*/

@Preview
@Composable
private fun Display() {
    BackdropPosterTitleSection(
        data = DetailsModel(
            id = 10,
            title = "",
            averageVoting = 0.0,
            backdropPath = "",
            releaseYear = 2,
            posterPath = "",
            durationInMinutes = 22,
            genre = "3",
            aboutMovie = "dbsdsd"
        )
    )
}


