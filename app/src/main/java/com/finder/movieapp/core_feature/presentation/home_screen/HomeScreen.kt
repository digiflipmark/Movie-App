package com.finder.movieapp.core_feature.presentation.home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finder.movieapp.core_feature.presentation.comman.SearchBar
import com.finder.movieapp.core_feature.presentation.search_screen.SearchScreen
import com.finder.movieapp.ui.theme.MovieAppTheme

@Composable
fun HomeScreen() {

    val state = rememberLazyListState()

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
                    .height(42.dp),
                text = "",
                onTextChange = {}, readonly = true, onClick = {}
            )

            Spacer(modifier = Modifier.height(20.dp))


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


@Preview
@Composable
fun HeaderTextPreview() {
    MovieAppTheme {

        HeaderText(modifier = Modifier, text = "What do you want to watch?")
    }
}

@Preview
@Composable
private fun DisplayHome() {

    MovieAppTheme {
        HomeScreen()
    }
}