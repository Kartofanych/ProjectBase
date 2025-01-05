package com.search.impl.ui.composables

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.common.composables.NetworkImage
import com.example.travelling.common.composables.ReviewStarsComponent
import com.example.travelling.common.composables.touchAction
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.regularTextStyle
import com.search.impl.data.models.local.Activity
import com.search.impl.ui.ListAction

@Composable
fun ActivityGroupItem(item: Activity, onAction: (ListAction) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .touchAction {
                onAction(ListAction.OpenActivity(item.id))
            }
            .padding(horizontal = 20.dp)
    ) {
        NetworkImage(
            url = item.icon,
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = item.title,
                style = mediumTextStyle.copy(fontSize = 14.sp, color = Color.Black),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            if (item.rating != 0f) {
                ReviewStarsComponent(
                    item.rating.toString(),
                    item.starsCount,
                )
                Spacer(modifier = Modifier.height(5.dp))
            }

            Text(
                text = item.subtitle,
                style = regularTextStyle.copy(fontSize = 10.sp, color = Color.Black),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
