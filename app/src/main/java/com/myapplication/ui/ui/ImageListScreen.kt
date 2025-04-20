package com.myapplication.ui.ui

// Presentation Layer
import ImageCarousel
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.myapplication.ImageListItem
import com.myapplication.provideer.ImagePreviewParameterProvider
import com.myapplication.remote.DataFetchState
import com.myapplication.ui.showStatisticsBottomSheet
import com.myapplication.viewmodel.HomeViewModel
import com.satta.d_matka.error.StandardError


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageListScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val listState = rememberLazyListState()
    val context = LocalContext.current

    var searchQuery by remember { viewModel.searchQuery }
    val images = viewModel.pagerFlow.collectAsLazyPagingItems()

    val filteredItems = remember(searchQuery, images.itemSnapshotList) {
        if (searchQuery.isBlank()) {
            images.itemSnapshotList.items
        } else {
            images.itemSnapshotList.items.filter {
                it.title.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    // Track first visible item index and calculate current page
    val firstVisibleItemIndex = remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val currentPage = (firstVisibleItemIndex.value / 20) + 1

    // Track when scrolling back to top to update bottom sheet data
    LaunchedEffect(firstVisibleItemIndex) {
        val dataList = images.itemSnapshotList.items.map { it.title }
        showStatisticsBottomSheet(context, dataList, page = currentPage+1)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xF6FBF8))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
        ) {
            item {
                ImageCarousel(viewModel.sliderImages)
            }

            stickyHeader {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchChange = { searchQuery = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }


            if (searchQuery.isNotBlank()) {
                itemsIndexed(filteredItems) { index, item ->
                    ImageItemView(item)
                }
            } else {
                items(images.itemCount) { index ->
                    images[index]?.let { ImageItemView(it) }
                }

                images.apply {
                    when (loadState.append) {
                        is LoadState.Loading -> item { ShowLoading() }
                        is LoadState.Error -> item {
                            val error = (loadState.append as LoadState.Error).error
                            ShowError(error.message ?: "Error loading more")
                        }
                        else -> {}
                    }
                }
            }
        }
        // Floating Action Button
        FloatingActionButton(
            onClick = {
                // Collect the list of item titles
                val dataList = images.itemSnapshotList.items.map { it.title }

                // Show the statistics in the bottom sheet for the current page
                showStatisticsBottomSheet(context, dataList, page = currentPage)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(50) // Ensures it's circular
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert, // Or use any icon
                contentDescription = "Add",
                tint = Color.White
            )
        }
    }


}


@Composable
fun ShowLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ShowError(error: String) {
    val context = LocalContext.current
    LaunchedEffect(error) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }
}

@Composable
fun ImageItemView(item: ImageListItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD2E7E1)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(item.image),
                contentDescription = item.details,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.details,
                    fontWeight = FontWeight.Light,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }
    }

}

// Preview Composable to see the layout with ImagePreviewData
@Preview(showBackground = true)
@Composable
fun PreviewImageItemView(
    @PreviewParameter(ImagePreviewParameterProvider::class) imageData: ImageListItem,
) {
    ImageItemView(imageData)
}