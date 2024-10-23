package com.example.multimodulepractice.common.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    scale: Scale = Scale.FILL,
    contentScale: ContentScale = ContentScale.Crop
) {
    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .scale(scale)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier,
    )
}
