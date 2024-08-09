package com.finder.movieapp.core_feature.presentation.home_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.finder.movieapp.R
import com.finder.movieapp.core_feature.data.remote.dto.Result
import com.finder.movieapp.core_feature.domain.util.MoviesGenre
import com.finder.movieapp.core_feature.presentation.comman.SearchBar
import com.finder.movieapp.core_feature.presentation.home_screen.componet.MovieCard
import com.finder.movieapp.core_feature.presentation.home_screen.componet.MoviesLoadState
import com.finder.movieapp.core_feature.presentation.home_screen.componet.RetryCard
import com.finder.movieapp.core_feature.presentation.home_screen.componet.TrendingMovieCard
import com.finder.movieapp.core_feature.presentation.util.componets.MoviesTabLayout
import com.finder.movieapp.core_feature.presentation.util.componets.gridItems
import com.finder.movieapp.ui.theme.Gray600
import com.finder.movieapp.ui.theme.MovieAppTheme

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {

    val state = rememberLazyListState()
    val trendingMovies = homeViewModel.trendingMovies.collectAsLazyPagingItems()
    val multipleMovies = homeViewModel.pageFlow.collectAsLazyPagingItems()
    val selectedIndex = homeViewModel.tabLayoutMoviesState.collectAsState().value
    val movieCategory = rememberMoviesCategories()

    LazyColumn(modifier = Modifier.fillMaxSize(), state = state) {

        item {

            Spacer(modifier = Modifier.height(10.dp))

            HeaderText(
                modifier = Modifier.padding(horizontal = 24.dp), text = "What do you want to watch?"
            )

            Spacer(modifier = Modifier.height(24.dp))

            SearchBar(modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .height(50.dp),
                text = "",
                onTextChange = {},
                readonly = true,
                onClick = {})

            Spacer(modifier = Modifier.height(20.dp))

            TrendingMovieSection(trending = trendingMovies, onClick = {

            })

            Spacer(modifier = Modifier.height(24.dp))

            MoviesTabLayout(movieCategory,
                selectedIndex = selectedIndex.selectedIndex,
                onTabSelect = {
                    homeViewModel.changeMoviesTab(it.tab)
                })
            Spacer(modifier = Modifier.height(20.dp))
        }

        gridItems(
            multipleMovies,
            3,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalSpace = 18.dp,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            val localContext = LocalContext.current
            it?.let {
                MovieCard(
                    movieItem = it, modifier = Modifier
                        .width(100.dp)
                        .height(146.dp)
                        .padding(horizontal = 11.dp)
                ) {

                    Toast.makeText(localContext, it.title, Toast.LENGTH_SHORT).show()
                }

            }


        }

    }

}


@Composable
fun TrendingMovieSection(trending: LazyPagingItems<Result>, onClick: (Result) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(start = 24.dp),
            modifier = Modifier.fillMaxSize()
        ) {

            items(trending.itemCount) {

                trending[it]?.let { result ->

                    TrendingMovieCard(
                        movieItem = result,
                        onClick = { onClick(result) },
                        number = it + 1
                    )
                }

            }
        }

        val loadingState = trending.loadState
        MoviesLoadState(loadingState, trending)
    }
}

@Composable
fun RetryItem(e: LoadState.Error? = null, retryRequest: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        RetryCard(
            modifier = Modifier, message = e?.error?.message.toString(), onClick = {
                retryRequest()
            }
        )
    }
}


@Composable
fun HeaderText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text, style = MaterialTheme.typography.headlineLarge, modifier = modifier
    )
}


@Composable
fun rememberMoviesCategories(): List<MovieTab> {

    return listOf(
        MovieTab(
            title = stringResource(id = R.string.now_playing), tab = MoviesGenre.NOW_PLAYING
        ), MovieTab(
            title = stringResource(id = R.string.upcoming), tab = MoviesGenre.UPCOMING
        ), MovieTab(
            title = stringResource(id = R.string.top_rated), tab = MoviesGenre.TOP_RATED
        ), MovieTab(
            title = stringResource(id = R.string.popular), tab = MoviesGenre.POPULAR
        )
    )
}

