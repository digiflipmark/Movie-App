package com.finder.movieapp.core_feature.presentation.home_screen.componet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finder.movieapp.ui.theme.MovieAppTheme
import com.finder.movieapp.ui.theme.White200

@Composable
fun RetryCard(modifier: Modifier = Modifier, message: String?, onClick: () -> Unit) {

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {

        Button(
            onClick = onClick,
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            ), modifier = modifier
        ) {

            Text(
                text = "Retry",
                style = MaterialTheme.typography.headlineLarge,
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        message?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.headlineLarge, color = White200.copy(alpha = 0.8f)
            )
        }
    }

}

@Preview
@Composable
private fun Disply() {

    MovieAppTheme {
        RetryCard(
            modifier = Modifier.height(40.dp),
            message = "Check your connection",
            onClick = {})
    }
}