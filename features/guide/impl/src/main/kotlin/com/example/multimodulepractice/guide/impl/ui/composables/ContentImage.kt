package com.example.multimodulepractice.guide.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun ContentImage(url: String) {
    val localContext = LocalContext.current
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(localContext)
            .data(url)
            .scale(Scale.FILL)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .padding(start = 1.dp, end = 1.dp)
                    .fillMaxSize()
                    .background(
                        shimmerBrush(
                            targetValue = 1300f,
                            showShimmer = true
                        )
                    )
            )
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}