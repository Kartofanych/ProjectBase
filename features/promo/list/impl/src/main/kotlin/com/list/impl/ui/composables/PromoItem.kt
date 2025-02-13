package com.list.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.common.composables.ActivityType
import com.example.travelling.common.composables.DefaultButton
import com.example.travelling.common.composables.NetworkImage
import com.example.travelling.common.composables.ReviewStarsComponent
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.semiboldTextStyle
import com.list.impl.data.models.local.PromoCode
import com.list.impl.ui.PromoListAction

@Composable
fun PromoItem(item: PromoCode, onAction: (PromoListAction) -> Unit) {

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp)
            .padding(top = 24.dp, bottom = 16.dp),
    ) {
        Row(Modifier.fillMaxWidth()) {

            NetworkImage(
                modifier = Modifier
                    .size(85.dp)
                    .clip(RoundedCornerShape(16.dp)),
                url = item.imageUrl
            )

            Spacer(Modifier.width(12.dp))

            Column {
                ActivityType(tag = item.tag)

                Spacer(Modifier.height(6.dp))

                Text(
                    text = item.title,
                    style = semiboldTextStyle.copy(fontSize = 16.sp, color = Color.Black),
                )

                Spacer(Modifier.height(4.dp))

                ReviewStarsComponent(
                    rating = item.rating.toString(),
                    starCount = item.rating.toInt(),
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = item.subtitle,
            style = mediumTextStyle.copy(fontSize = 16.sp, color = Color(0xFF959595)),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.height(16.dp))

        DefaultButton(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(),
            backgroundColor = Color(0xFF74A3FF),
            onClick = { onAction(PromoListAction.OnOpenPromoItem(item.token)) },
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = "Использовать!",
                style = mediumTextStyle.copy(fontSize = 15.sp, color = Color.White),
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = item.endDate,
            style = semiboldTextStyle.copy(fontSize = 12.sp, color = Color(0xFF838383)),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            maxLines = 1
        )
    }
}
