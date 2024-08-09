package com.finder.movieapp.core_feature.presentation.home_screen.componet

import androidx.compose.runtime.Composable
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.finder.movieapp.core_feature.data.remote.dto.Result
import com.finder.movieapp.core_feature.presentation.home_screen.RetryItem

@Composable
fun MoviesLoadState(loadingState: CombinedLoadStates, trending: LazyPagingItems<Result>) {

    when (loadingState.refresh) {
        is LoadState.Loading -> {
            CircularLoading()
        }

        is LoadState.Error -> {
            val e = loadingState.refresh as LoadState.Error
            RetryItem(e, retryRequest = { trending.retry() })
        }

        is LoadState.NotLoading -> {}
    }

}