package com.inno.landmark.ui.landmark_content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
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
import com.example.common.composables.shimmerBrush

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageSlider(
    imageUrls: List<String>,
    sheetState: SheetState,
    columnHeightPx: MutableState<Float>
) {
    val pageCounter = imageUrls.size
    val pagerState = rememberPagerState(pageCount = { pageCounter })

    val progress = remember {
        derivedStateOf {
            try {
                sheetState.requireOffset() / columnHeightPx.value
            } catch (exception: Exception) {
                1f
            }
        }
    }

    Box(
        Modifier
            .height(maxOf(400f * (1 - progress.value), 200f).dp)
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
                    AnimatedVisibility(visible = progress.value == 1f) {
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
