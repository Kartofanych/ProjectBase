package com.search.impl.ui.composables

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.multimodulepractice.common.composables.ActivityType
import com.example.multimodulepractice.common.composables.ReviewStarsComponent
import com.example.multimodulepractice.common.composables.touchAction
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.search.impl.data.models.local.Attraction
import com.search.impl.ui.ListAction

@Composable
fun RecommendedAttractionView(
    attraction: Attraction,
    context: Context,
    onListAction: (ListAction) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(200.dp)
            .touchAction {
                onListAction(ListAction.OpenAttraction(attraction.id))
            }
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(16.dp))
            .padding(bottom = 10.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(attraction.icon)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(10.dp))

        ActivityType(
            modifier = Modifier.padding(horizontal = 5.dp),
            tag = attraction.type
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = attraction.name,
            style = semiboldTextStyle.copy(fontSize = 14.sp),
            modifier = Modifier
                .padding(top = 3.dp)
                .padding(horizontal = 5.dp)
                .fillMaxWidth(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(3.dp))

        if (attraction.rating != 0f) {
            ReviewStarsComponent(
                rating = attraction.rating.toString(),
                starCount = attraction.stars
            )
        }
    }
}
