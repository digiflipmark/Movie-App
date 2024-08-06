package com.finder.movieapp.core_feature.presentation.movie_navigatore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.finder.movieapp.R
import com.finder.movieapp.core_feature.presentation.home_screen.HomeScreen
import com.finder.movieapp.core_feature.presentation.movie_navigatore.components.MoviesBottomNavigation
import com.finder.movieapp.core_feature.presentation.search_screen.SearchScreen
import com.finder.movieapp.core_feature.presentation.watchlist_screen.BookMarkScreen
import com.finder.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieNavScreen() {

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value


    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_save, text = "Bookmark"),
        )
    }


    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = when (backStackState?.destination?.route) {
        MovieRoute.HomeScreen.route -> 0
        MovieRoute.SearchScreen.route -> 1
        MovieRoute.WatchListScreen.route -> 2
        else -> {
            0
        }
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == MovieRoute.HomeScreen.route || backStackState?.destination?.route == MovieRoute.SearchScreen.route || backStackState?.destination?.route == MovieRoute.WatchListScreen.route
    }



    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {

            MoviesBottomNavigation(item = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->

                    when (index) {
                        0 -> navigateToTab(
                            navController = navController, route = MovieRoute.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController, route = MovieRoute.SearchScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController, route = MovieRoute.WatchListScreen.route
                        )
                    }
                })
        }
    }, content = {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            modifier = Modifier.padding(bottom = bottomPadding),
            navController = navController,
            startDestination = MovieRoute.HomeScreen.route
        ) {

            composable(route = MovieRoute.HomeScreen.route) {

                HomeScreen()
            }

            composable(route = MovieRoute.SearchScreen.route) {

                SearchScreen()
            }

            composable(route = MovieRoute.WatchListScreen.route) {

                BookMarkScreen()
            }

        }
    })
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Preview
@Composable
private fun Disp() {
    MovieAppTheme {

        MovieNavScreen()
    }
}