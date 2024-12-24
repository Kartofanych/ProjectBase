package com.attraction.impl.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attraction.impl.data.models.local.CloseObject
import com.attraction.impl.ui.AttractionAction
import com.example.multimodulepractice.common.composables.NetworkImage
import com.example.multimodulepractice.common.composables.ReviewStarsComponent
import com.example.multimodulepractice.common.composables.touchAction
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.regularTextStyle

@Composable
fun ServiceItem(item: CloseObject, onAction: (AttractionAction) -> Unit) {

    Row(
        modifier = Modifier
            .width(278.dp)
            .height(90.dp)
            .touchAction {
                onAction(AttractionAction.OpenObject(id = item.id, type = item.type))
            }
    ) {

        NetworkImage(
            url = item.icon,
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(16.dp)),
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
                style = mediumTextStyle.copy(fontSize = 10.sp, color = Color.Black),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = item.distance,
                style = regularTextStyle.copy(fontSize = 10.sp, color = Color(0xFF959595)),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
