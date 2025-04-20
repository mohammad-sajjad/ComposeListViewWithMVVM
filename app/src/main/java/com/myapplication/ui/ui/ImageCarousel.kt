import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.myapplication.models.SliderItems
import com.myapplication.provideer.SliderPreviewParameterProvider
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun ImageCarousel(items: List<SliderItems>) {
    // Remember the pager state, with initial page and calculate page count dynamically
    val pagerState = rememberPagerState(
        initialPage = 0 // Start from the first page
    )

    Column {
        HorizontalPager(
            count = items.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 8.dp),
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) { page ->
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                Image(
                    painter = rememberAsyncImagePainter(items[page].image),
                    contentDescription = items[page].title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
    }
}

// Preview Composable to see the layout with ImagePreviewData
@Preview(showBackground = true)
@Composable
fun PreviewImageItemView(
    @PreviewParameter(SliderPreviewParameterProvider ::class) imageData: List<SliderItems>,
) {
    ImageCarousel(imageData)
}
