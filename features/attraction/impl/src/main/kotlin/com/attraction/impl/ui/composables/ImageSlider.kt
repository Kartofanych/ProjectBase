package com.attraction.impl.ui.composables

import android.view.MotionEvent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import coil.size.Scale
import com.attraction.impl.data.models.local.Attraction
import com.attraction.impl.ui.AttractionAction
import com.example.multimodulepractice.attraction.impl.R
import com.example.multimodulepractice.common.composables.shimmerBrush

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ImageSlider(
    attraction: Attraction,
    onAction: (AttractionAction) -> Unit
) {
    val pageCounter = attraction.images.size
    val pagerState = rememberPagerState(pageCount = { pageCounter })

    val selectedBack = remember { mutableStateOf(false) }
    val scaleBack by animateFloatAsState(if (selectedBack.value) 0.8f else 1f)

    val selectedLike = remember { mutableStateOf(false) }
    val scaleLike by animateFloatAsState(if (selectedLike.value) 0.8f else 1f)

    Box(
        Modifier
            .height(400.dp)
            .fillMaxWidth()
    ) {

        HorizontalPager(state = pagerState) { page ->
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(attraction.images[page])
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

        Row(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0x99FFFFFF), RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .scale(scaleBack)
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selectedBack.value = true
                            }

                            MotionEvent.ACTION_UP -> {
                                selectedBack.value = false
                                onAction(AttractionAction.OnBackPressed)
                            }

                            MotionEvent.ACTION_CANCEL -> {
                                selectedBack.value = false
                            }
                        }
                        true
                    }
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {},
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_back),
                    contentDescription = null,
                    tint = Color(0xFF2A2A2A),
                    modifier = Modifier.size(24.dp)
                )
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0x99FFFFFF), RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .scale(scaleLike)
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selectedLike.value = true
                            }

                            MotionEvent.ACTION_UP -> {
                                selectedLike.value = false
                                onAction(AttractionAction.OnLikeChanged)
                            }

                            MotionEvent.ACTION_CANCEL -> {
                                selectedLike.value = false
                            }
                        }
                        true
                    }
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {},
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(if (attraction.isLiked) R.drawable.ic_liked else R.drawable.ic_unliked),
                    contentDescription = null,
                    tint = Color(0xFF2A2A2A),
                    modifier = Modifier.size(24.dp)
                )
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
