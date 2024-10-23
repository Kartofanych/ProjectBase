package com.search_filters.impl.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.multimodulepractice.common.composables.ActivityType
import com.example.multimodulepractice.common.composables.ReviewStarsComponent
import com.example.multimodulepractice.common.data.models.local.ActivityEntity
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.regularTextStyle
import com.search_filters.impl.ui.SearchAction

@Composable
fun SearchActivity(entity: ActivityEntity, onAction: (SearchAction) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp)
            .height(108.dp)
            .clickable {
                onAction(SearchAction.ActivityClicked(entity))
            }
            .padding(horizontal = 18.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(entity.icon)
                .scale(Scale.FILL)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(108.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.fillMaxSize()) {

            ActivityType(entity.tag)

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = entity.title,
                style = mediumTextStyle.copy(fontSize = 14.sp, color = Color.Black),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(3.dp))

            if (entity.rating != 0f) {
                ReviewStarsComponent(
                    entity.rating.toString(),
                    entity.starCount
                )
                Spacer(modifier = Modifier.height(3.dp))
            }

            Text(
                text = entity.description,
                style = mediumTextStyle.copy(fontSize = 10.sp, color = Color.Black),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = entity.subtitle,
                style = regularTextStyle.copy(fontSize = 10.sp, color = Color(0xFF959595)),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}