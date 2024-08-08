package com.finder.movieapp.core_feature.presentation.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.finder.movieapp.core_feature.data.remote.dto.Result
import com.finder.movieapp.core_feature.presentation.comman.SearchBar
import com.finder.movieapp.core_feature.presentation.home_screen.componet.TrendingMovieCard

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {

    val state = rememberLazyListState()
    val trendingMovies = homeViewModel.trendingMovies.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize(), state = state) {

        item {
            Spacer(modifier = Modifier.height(10.dp))

            HeaderText(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = "What do you want to watch?"
            )

            Spacer(modifier = Modifier.height(24.dp))

            SearchBar(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                text = "",
                onTextChange = {}, readonly = true, onClick = {}
            )

            Spacer(modifier = Modifier.height(20.dp))

            TrendingMovieSection(trending = trendingMovies, onClick = {})

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
            contentPadding = PaddingValues(start = 24.dp), modifier = Modifier.fillMaxSize()
        ) {

            items(trending.itemCount) {

                trending[it]?.let { result ->

                    TrendingMovieCard(movieItem = result, onClick = { onClick(result) })
                }

            }
        }
    }
}

@Composable
fun HeaderText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier
    )
}
