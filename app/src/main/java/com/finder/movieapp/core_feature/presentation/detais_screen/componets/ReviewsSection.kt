package com.finder.movieapp.core_feature.presentation.detais_screen.componets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.finder.movieapp.core_feature.domain.model.ReviewModel

@Composable
fun ReviewsSection(reviews: LazyPagingItems<ReviewModel>) {

    LazyColumn {
        items(reviews.itemCount) {count->
            reviews[count]?.let {reviewItem->
                ReviewCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    review = reviewItem
                )
            }
        }
    }
}