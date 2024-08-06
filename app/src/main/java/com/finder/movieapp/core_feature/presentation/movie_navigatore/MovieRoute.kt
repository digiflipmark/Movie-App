package com.finder.movieapp.core_feature.presentation.movie_navigatore

sealed class MovieRoute(val route: String) {

    data object HomeScreen : MovieRoute("home_screen")
    data object SearchScreen : MovieRoute("search_screen")
    data object WatchListScreen : MovieRoute("watch_list_screen")
    data object MovieDetailScreen : MovieRoute("movie_detail_screen")
}