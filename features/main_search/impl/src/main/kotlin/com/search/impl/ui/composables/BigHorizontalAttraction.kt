package com.search.impl.ui.composables

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import coil.size.Scale
import com.example.travelling.common.ChipCard
import com.example.travelling.common.composables.shimmerBrush
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.regularTextStyle
import com.example.travelling.common.theme.semiboldTextStyle
import com.main.common.data.local.Attraction
import com.search.impl.ui.ListAction

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BigHorizontalAttraction(
    context: Context,
    attraction: Attraction,
    onListAction: (ListAction) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(11.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onListAction(ListAction.OpenAttraction(attraction.id))
            }
            .padding(bottom = 12.dp),
    ) {

        LoadingImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
                .clip(RoundedCornerShape(16.dp)),
            attraction.icon,
            context
        )

        Text(
            text = attraction.name,
            style = semiboldTextStyle.copy(fontSize = 16.sp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp)
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            for (category in attraction.categories) {
                ChipCard(text = category.name, activeColor = category.color)
            }
        }

        Text(
            text = attraction.shortInfo,
            style = mediumTextStyle.copy(fontSize = 14.sp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Дата основания: ${attraction.dateCreation}",
            style = regularTextStyle.copy(fontSize = 12.sp, color = Color(0xFF959595)),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun LoadingImage(modifier: Modifier, url: String, context: Context) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .scale(Scale.FILL)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier,
    ) {
        val state = painter.state
        AnimatedVisibility(visible = state is AsyncImagePainter.State.Success) {
            SubcomposeAsyncImageContent()
        }
        AnimatedVisibility(visible = state !is AsyncImagePainter.State.Success) {
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
        }
    }
}