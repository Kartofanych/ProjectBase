package com.example.multimodulepractice.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.decode.BitmapFactoryDecoder
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Scale

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    type: ImageType = ImageType.PNG,
    scale: Scale = Scale.FILL,
    contentScale: ContentScale = ContentScale.Crop
) {
    val decoder =
        if (type == ImageType.SVG) SvgDecoder.Factory() else BitmapFactoryDecoder.Factory()

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .decoderFactory(decoder)
            .scale(scale)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier,
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
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

enum class ImageType {
    PNG,
    SVG
}