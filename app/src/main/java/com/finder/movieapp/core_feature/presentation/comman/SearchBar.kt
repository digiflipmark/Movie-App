package com.finder.movieapp.core_feature.presentation.comman

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.finder.movieapp.R
import com.finder.movieapp.ui.theme.Gray600
import com.finder.movieapp.ui.theme.MovieAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    readonly: Boolean = false, onClick: () -> Unit
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isClick = interactionSource.collectIsPressedAsState().value

    LaunchedEffect(key1 = isClick) {
        if (isClick) {
            onClick.invoke()
        }
    }

    Box(modifier = modifier) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            value = text,
            onValueChange = onTextChange,
            readOnly = readonly,
            placeholder = {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray600
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    modifier = Modifier.size(16.dp),
                    tint = Gray600
                )
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.bodySmall.copy(
                color = Color.White,
                fontSize = 16.sp
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                cursorColor = Color.White,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ), keyboardOptions = KeyboardOptions.Default,
            interactionSource = interactionSource
        )
    }

}


@Preview
@Composable
fun SearchBarPreview() {
    MovieAppTheme {

        SearchBar(
            modifier = Modifier,
            text = "",
            onTextChange = {},
            readonly = true, onClick = {}
        )
    }
}