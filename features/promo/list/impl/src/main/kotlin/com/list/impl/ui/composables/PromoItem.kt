package com.list.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.composables.NetworkImage
import com.example.multimodulepractice.common.composables.touchAction
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.promo.list.impl.R
import com.list.impl.data.models.local.PromoCode
import com.list.impl.ui.PromoListAction

@Composable
fun PromoItem(item: PromoCode, onAction: (PromoListAction) -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .touchAction {
                onAction(PromoListAction.OnOpenPromoItem(item.token))
            }
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 12.dp),
    ) {

        NetworkImage(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp)),
            url = item.imageUrl
        )

        Spacer(Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .height(100.dp)
                .width(178.dp)
                .padding(vertical = 8.dp)
        ) {

            Column {
                Text(
                    text = item.title,
                    style = semiboldTextStyle.copy(fontSize = 14.sp, color = Color.Black),
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    text = item.subtitle,
                    style = semiboldTextStyle.copy(fontSize = 16.sp, color = Color(0xFF959595)),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                text = item.endDate,
                style = semiboldTextStyle.copy(fontSize = 10.sp, color = Color.Black),
                modifier = Modifier.align(Alignment.BottomStart),
                maxLines = 1
            )
        }

        Spacer(Modifier.width(12.dp))

        Icon(
            painter = painterResource(R.drawable.icon_back),
            modifier = Modifier
                .align(Alignment.Bottom)
                .rotate(180f)
                .size(16.dp),
            contentDescription = null,
            tint = Color.Black
        )
    }
}
