package com.myapplication.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchQuery: String,
    onSearchChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchQuery, // Managing state for the search query
        onValueChange = onSearchChange,
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search")
        },
        placeholder = {
            Text("Search")
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp)), // Solid background
        shape = RoundedCornerShape(8.dp), // Rounded corners
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xffe5eaea),
            focusedContainerColor = Color(0xffe5eaea),
            focusedIndicatorColor = Color(0xffe5eaea),
            unfocusedIndicatorColor = Color(0xffe5eaea)
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchBarPreview() {
    var searchText by remember { mutableStateOf("") }

    SearchBar(
        searchQuery = searchText,
        onSearchChange = { searchText = it }
    )
}
