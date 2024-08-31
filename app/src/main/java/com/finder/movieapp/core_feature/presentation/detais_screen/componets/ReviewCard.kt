package com.finder.movieapp.core_feature.presentation.detais_screen.componets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.finder.movieapp.R
import com.finder.movieapp.core_feature.domain.model.ReviewModel
import com.finder.movieapp.ui.theme.BlueLight
import com.finder.movieapp.ui.theme.PoppinsFont

@Composable
fun ReviewCard(modifier: Modifier = Modifier, review: ReviewModel) {
    Box(modifier = modifier) {
        val context = LocalContext.current
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = ImageRequest.Builder(context).crossfade(true)
                        .placeholder(R.drawable.man_review)
                        .data(review.avatarPath).error(R.drawable.man_review).build(),
                    contentDescription = review.author, modifier = Modifier
                        .clip(CircleShape)
                        .size(44.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = review.rating.toString(),
                    style = TextStyle(color = BlueLight, fontSize = 12.sp)
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = review.author,
                    style = TextStyle(
                        fontFamily = PoppinsFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp, color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = review.content,
                    style = TextStyle(
                        fontFamily = PoppinsFont,
                        fontSize = 12.sp,
                        color = Color.White
                    ),
                    maxLines = 7,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
private fun Test() {
    ReviewCard(
        review = ReviewModel(
            author = "Brent Marchant",
            rating = "6.0".toDouble(),
            avatarPath = "https://image.tmdb.org/t/p/w500/yz2HPme8NPLne0mM8tBnZ5ZWJzf.jpg",
            content = "a and chemistry to characters with some emotional depth. Dan Mindel's cinematography and Benjamin Wallfisch's score add to the immersion layer of the summer blockbuster.\\r\\n\\r\\nIt doesn't bring anything new to the genre, nor does it need to, as it fulfills its sole, valid purpose of entertaining its target audience while still respecting the victims of natural disasters, " +
                    "reminding us of the importance of humanity and altruism in times of crisis.\\\"\\r\\n\\r\\nRating: B-"
        )
    )
}