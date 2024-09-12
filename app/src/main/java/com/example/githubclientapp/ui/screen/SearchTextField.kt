package com.example.githubclientapp.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubclientapp.R
import com.example.githubclientapp.ui.theme.AppTheme

@Composable
fun SearchTextField(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
        )
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(12.dp),
            )
            .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            tint = Color.Gray,
            imageVector = Icons.Default.Search,
            contentDescription = "search",
            modifier = Modifier.padding(start = 4.dp),
        )

        SearchInput(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
        )
    }
}

@Composable
fun SearchInput(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showClearButton by remember { mutableStateOf(query.isNotEmpty()) }

    TextField(
        value = query,
        onValueChange = {
            showClearButton = it.isNotEmpty()
            onQueryChange(it)
        },
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            cursorColor = Blue,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        trailingIcon = {
            if (showClearButton) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        tint = Color.Gray,
                        imageVector = Icons.Default.Close,
                        contentDescription = "clear",
                    )
                }
            }
        },
        textStyle = TextStyle(fontSize = 18.sp),
        label = {
            Text(
                text = stringResource(R.string.searchInputText_hint),
                color = Color.Gray
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
    )
}

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun SearchTextFieldPreview() {
    AppTheme {
        SearchTextField(
            query = "",
            onQueryChange = {},
            onSearch = {},
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun SearchTextFieldPreviewDarkMode() {
    AppTheme(darkTheme = true) {
        SearchTextField(
            query = "",
            onQueryChange = {},
            onSearch = {},
        )
    }
}
