package com.attraction.impl.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import coil.size.Scale
import com.example.multimodulepractice.common.composables.shimmerBrush

@Composable
fun ImageSlider(
    imageUrls: List<String>
) {
    val pageCounter = imageUrls.size
    val pagerState = rememberPagerState(pageCount = { pageCounter })

    Box(
        Modifier
            .height(400.dp)
            .fillMaxWidth()
    ) {

        HorizontalPager(state = pagerState) { page ->
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrls[page])
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    Box(
                        modifier = Modifier
                            .padding(start = 1.dp, end = 1.dp)
                            .fillMaxWidth()
                            .height(175.dp)
                            .background(
                                shimmerBrush(
                                    targetValue = 1300f,
                                    showShimmer = true
                                )
                            )
                    )
                } else {
                    SubcomposeAsyncImageContent()
                    Box(
                        modifier = Modifier
                            .animateContentSize()
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Transparent,
                                        Color(0xFF262525)
                                    ),
                                    startY = 180f
                                )
                            )
                    )
                }
            }
        }

        Indicator(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            pagerState = pagerState
        )
    }
}
