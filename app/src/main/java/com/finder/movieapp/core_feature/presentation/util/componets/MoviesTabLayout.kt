package com.finder.movieapp.core_feature.presentation.util.componets

import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.finder.movieapp.core_feature.presentation.home_screen.MovieTab

@Composable
fun MoviesTabLayout(
    tabs: List<MovieTab>,
    selectedIndex: Int,
    onTabSelect: (MovieTab) -> Unit,
) {

    ScrollableTabRow(selectedTabIndex = selectedIndex) {

        tabs.forEachIndexed { index, movieTab ->

            Tab(selected = index == selectedIndex, onClick = {
                onTabSelect(tabs[index])
            }, text = { Text(movieTab.title) })
        }
    }
}