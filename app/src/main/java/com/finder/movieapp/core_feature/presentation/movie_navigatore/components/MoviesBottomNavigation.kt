package com.finder.movieapp.core_feature.presentation.movie_navigatore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finder.movieapp.R
import com.finder.movieapp.core_feature.presentation.movie_navigatore.BottomNavigationItem
import com.finder.movieapp.ui.theme.BlackPurple
import com.finder.movieapp.ui.theme.BlueLight
import com.finder.movieapp.ui.theme.Gray600
import com.finder.movieapp.ui.theme.MovieAppTheme

@Composable
fun MoviesBottomNavigation(
    item: List<BottomNavigationItem>, selectedItem: Int, onItemClick: (Int) -> Unit
) {

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp),
        containerColor = BlackPurple,
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = BlueLight)
            )
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                item.forEachIndexed { index, item ->

                    BottomNavigationUi(
                        selectedItem = index == selectedItem,
                        onClick = { onItemClick(index) }, item
                    )
                }

            }
        }

    }

}


@Composable
fun RowScope.BottomNavigationUi(
    selectedItem: Boolean,
    onClick: () -> Unit,
    item: BottomNavigationItem,
) {
    NavigationBarItem(
        selected = selectedItem, onClick = onClick, icon = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = "",
                    modifier = Modifier
                        .width(21.dp)
                        .height(24.dp)
                )
                Text(
                    text = item.text, style = MaterialTheme.typography.headlineMedium, maxLines = 1
                )
            }
        }, colors = NavigationBarItemDefaults.colors(
            selectedIconColor = BlueLight,
            unselectedIconColor = Gray600,
            indicatorColor = BlackPurple
        )
    )
}


@Preview(showBackground = true)
@Composable
private fun Displ() {
    MovieAppTheme {
        MoviesBottomNavigation(item = listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_save, text = "Bookmark"),
        ), selectedItem = 0, onItemClick = {})
    }
}