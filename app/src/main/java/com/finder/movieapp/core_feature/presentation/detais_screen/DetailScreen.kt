package com.finder.movieapp.core_feature.presentation.detais_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.finder.movieapp.R
import com.finder.movieapp.core_feature.domain.model.CastModel
import com.finder.movieapp.core_feature.domain.model.DetailsModel
import com.finder.movieapp.core_feature.domain.model.ReviewModel
import com.finder.movieapp.core_feature.presentation.detais_screen.componets.CastCard
import com.finder.movieapp.core_feature.presentation.detais_screen.componets.ReviewsSection
import com.finder.movieapp.core_feature.presentation.util.ImageType
import com.finder.movieapp.core_feature.presentation.util.componets.MovieImageCard
import com.finder.movieapp.core_feature.presentation.util.componets.ScreenToolBar
import com.finder.movieapp.core_feature.presentation.util.componets.TextIcon
import com.finder.movieapp.core_feature.presentation.util.componets.VerticaLine
import com.finder.movieapp.ui.theme.Black800
import com.finder.movieapp.ui.theme.Gray600
import com.finder.movieapp.ui.theme.Orange
import com.finder.movieapp.ui.theme.PoppinsFont

sealed class TabContent(val text: String) {
    data class AboutMovie(val aboutMovie: String) : TabContent(aboutMovie)
    data class Reviews(val reviews: String) : TabContent(reviews)
    data class Cast(val cast: String) : TabContent(cast)
}

@Composable
fun DetailScreen(
    navigateBackRequest: () -> Unit,
    state: DetailState,
    reviews: LazyPagingItems<ReviewModel>,
    onImageClick: (String?, ImageType) -> Unit,
    onBookMarkClick: () -> Unit
) {


    Column {

        Spacer(modifier = Modifier.height(20.dp))

        ScreenToolBar(
            title = stringResource(id = R.string.detail),
            navigateBackRequest = navigateBackRequest,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 22.dp),
            icon = painterResource(id = if (state.isSaved) R.drawable.ic_filled_bookmark else R.drawable.ic_unfilled_bookmark),
            iconClick = onBookMarkClick
        )

        if (state.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp), color = Color.White
            )
        }

        state.data?.let { movieDetails ->
            val movieDetailsTabs = rememberMovieDetailsTabs()
            Spacer(modifier = Modifier.height(20.dp))
            BackdropPosterTitleSection(movieDetails, onImageClick = onImageClick)
            Spacer(modifier = Modifier.height(20.dp))
            MovieInformation(movieDetails)
            Spacer(modifier = Modifier.height(24.dp))
            var movieDetailsSection by remember {
                mutableStateOf(movieDetailsTabs[0])
            }
            MoviesTabRow(updateSection = { tab ->
                movieDetailsSection = tab
            }, tabContent = movieDetailsTabs)
            Spacer(modifier = Modifier.height(24.dp))
            when (movieDetailsSection) {
                is TabContent.AboutMovie -> {
                    AboutMovieSection(movieDetails)
                }

                is TabContent.Cast -> {
                    CastSection(state.castData)
                }

                is TabContent.Reviews -> {
                    ReviewsSection(reviews = reviews)
                }

            }
        }

    }

}

@Composable
private fun AboutMovieSection(movieDetails: DetailsModel) {
    Text(
        text = movieDetails.aboutMovie,
        style = MaterialTheme.typography.headlineLarge.copy(
            color = Color.White,
            fontSize = 12.sp
        ), modifier = Modifier.padding(horizontal = 25.dp)
    )
}

@Composable
fun CastSection(castData: List<CastModel>?) {

    LazyVerticalGrid(
        contentPadding = PaddingValues(bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        columns = GridCells.Fixed(2)
    ) {
        castData?.let {
            items(it.count()) {
                val list = castData[it]
                CastCard(castModel = list)
            }
        }
    }

}

@Composable
fun MovieInformation(movieDetails: DetailsModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        horizontalArrangement = Arrangement.Center
    ) {

        TextIcon(
            text = movieDetails.releaseYear.toString(),
            icon = painterResource(id = R.drawable.ic_calendar),
            color = Gray600
        )
        Spacer(modifier = Modifier.width(12.dp))
        VerticaLine()
        Spacer(modifier = Modifier.width(12.dp))
        TextIcon(
            text = "${movieDetails.durationInMinutes} ${stringResource(id = R.string.minutes)}",
            icon = painterResource(id = R.drawable.ic_clock),
            color = Gray600
        )
        Spacer(modifier = Modifier.width(12.dp))
        VerticaLine()
        Spacer(modifier = Modifier.width(12.dp))
        TextIcon(
            text = movieDetails.genre,
            icon = painterResource(id = R.drawable.ic_ticket),
            color = Gray600
        )

    }
}

@Composable
fun BackdropPosterTitleSection(
    data: DetailsModel,
    onImageClick: (String?, ImageType) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(271.dp)
    ) {


        MovieBackDrop(
            modifier = Modifier.height(210.dp),
            data.backdropPath,
            data.averageVoting,
            onImageClick = onImageClick
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(start = 28.dp, end = 28.dp), verticalAlignment = Alignment.Bottom
        ) {

            MovieImageCard(
                imageUrl = data.posterPath,
                modifier = Modifier
                    .height(140.dp)
                    .width(95.dp)
                    .clickable { onImageClick(data.posterPath, ImageType.POSTER) },
                shapes = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            //For title
            Text(
                text = data.title,
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 2,
            )
        }
    }


}

@Composable
fun MovieBackDrop(
    modifier: Modifier = Modifier, backDropPath: String, averageRating: Double,
    onImageClick: (String?, ImageType) -> Unit
) {

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
                .height(210.dp)
                .clickable { onImageClick(backDropPath, ImageType.BACKDROP) },
            shapes = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
        )

        MovieRating(
            rating = averageRating,
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(end = 11.dp, bottom = 9.dp)
        )
    }
}

@Composable
fun MovieRating(rating: Double, modifier: Modifier = Modifier) {

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
        TextIcon(
            text = rating.toString(),
            icon = painterResource(id = R.drawable.ic_star),
            color = Orange
        )

    }
}

@Composable
fun MoviesTabRow(
    updateSection: (TabContent) -> Unit,
    tabContent: List<TabContent>,
) {

    MovieTabRowContent(
        tabs = tabContent,
        onTabSelect = updateSection
    )
}

@Composable
fun MovieTabRowContent(
    tabs: List<TabContent>,
    onTabSelect: (TabContent) -> Unit,
) {

    var tabSelectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    ScrollableTabRow(
        selectedTabIndex = tabSelectedIndex,
        containerColor = Color.Transparent,
        modifier = Modifier
            .height(41.dp),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(
                        currentTabPosition = tabPositions[tabSelectedIndex],
                    )
                    .height(4.dp),
                color = MaterialTheme.colorScheme.surface,
            )
        },
        divider = {},
        edgePadding = 30.dp,
    ) {

        tabs.forEachIndexed { index, tab ->

            Tab(
                selected = tabSelectedIndex == index,
                onClick = {
                    tabSelectedIndex = index
                    onTabSelect(tabs[index])
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp),
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White,
            ) {

                Text(
                    text = tab.text,
                    style = TextStyle(
                        fontFamily = PoppinsFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp, color = Color.White
                    )
                )

            }
        }
    }

}

@Composable
fun rememberMovieDetailsTabs(): List<TabContent> {
    return listOf(
        TabContent.AboutMovie(stringResource(id = R.string.about_movie)),
        TabContent.Reviews(stringResource(id = R.string.review)),
        TabContent.Cast(stringResource(id = R.string.cast)),
    )
}

/*@Preview
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
        ), onImageClick = { _, _ -> }
    )
}*/


