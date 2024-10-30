package com.example.multimodulepractice.common.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
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
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .decoderFactory(decoder)
            .scale(scale)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier,
    )
}

enum class ImageType {
    PNG,
    SVG
}