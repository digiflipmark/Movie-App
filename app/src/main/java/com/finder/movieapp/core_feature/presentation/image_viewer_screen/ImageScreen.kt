package com.finder.movieapp.core_feature.presentation.image_viewer_screen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.finder.movieapp.R
import com.finder.movieapp.core_feature.presentation.util.Constants
import com.finder.movieapp.core_feature.presentation.util.ImageType
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun ImageScreen(
    imagePath: String,
    type: ImageType?,
    downloadViewModel: DownloadViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(Color.Black)
    Log.e("TAG", "ImageScreen: ${imagePath}")
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val state = downloadViewModel.downloadState.collectAsState().value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        if (state.loading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
            )
        }

        LaunchedEffect(key1 = true) {
            downloadViewModel.message.collect {
                Toast.makeText(
                    context,
                    it,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        Column(
            Modifier
                .align(Alignment.TopEnd)
                .padding(top = 10.dp, end = 10.dp)
                .size(50.dp)

        ) {

            IconButton(onClick = { expanded = !expanded }) {

                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "",
                    tint = Color.White
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color.White)
            ) {
                DropdownMenuItem(text = {
                    Text(
                        text = "Save"
                    )
                }, onClick = {
                    expanded = false
                    if (sdk29AndUp()) {
                        downloadViewModel.downloadImage(imagePath)
                    }
                })
            }
        }

        val imageRequest =
            ImageRequest.Builder(LocalContext.current).placeholder(R.drawable.loading)
                .error(R.drawable.error_icon).crossfade(true)
                .data(Constants.IMAGES_BASE_PATH + imagePath).build()
        AsyncImage(
            model = imageRequest,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(if (type == ImageType.BACKDROP) 16 / 9f else 3 / 4f)
                .align(Alignment.Center),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
private fun ImageViewerPreview() {

    ImageScreen(
        imagePath = Constants.IMAGES_BASE_PATH + "lgkPzcOSnTvjeMnuFzozRO5HHw1.jpg",
        type = ImageType.POSTER
    )
}

fun sdk29AndUp(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
}