package com.finder.movieapp.core_feature.presentation.watchlist_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finder.movieapp.R
import com.finder.movieapp.core_feature.presentation.util.componets.ScreenToolBar
import com.finder.movieapp.core_feature.presentation.watchlist_screen.componet.NoResultScreen
import com.finder.movieapp.core_feature.presentation.watchlist_screen.componet.WatchListDetailCard
import com.finder.movieapp.ui.theme.MovieAppTheme


@Composable
fun WatchListScreen(state: WatchListState, navigateUpBack: () -> Unit, onClick: (id:Int) -> Unit) {
    val data = state.data
    val isEmpty = state.empty


    Box(modifier = Modifier.fillMaxSize()) {

        if (isEmpty) {
            NoResultScreen(modifier = Modifier.align(Alignment.Center))
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(20.dp))
            ScreenToolBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .padding(horizontal = 22.dp),
                title = stringResource(id = R.string.watch_list),
                navigateBackRequest = navigateUpBack
            )
            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                contentPadding = PaddingValues(24.dp)
            ) {
                items(data) { detail ->
                    WatchListDetailCard(detailsModel = detail, onClick = {onClick(detail.id)})
                }
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun WatchListPrev() {
    MovieAppTheme {
        WatchListScreen(state = WatchListState(data = emptyList()), navigateUpBack = {}, onClick = {})
    }
}