package com.example.multimodulepractice.main.impl.ui.compose_elements

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.main.impl.R
import com.example.multimodulepractice.main.impl.data.local_models.list.Attraction
import com.example.multimodulepractice.main.impl.ui.list.ListAction

@Composable
fun VerticalAttractionView(
    attraction: Attraction,
    context: Context,
    onListAction: (ListAction) -> Unit
) {
    Box(
        modifier = Modifier
            .height(270.dp)
            .width(170.dp)
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onListAction(ListAction.OpenAttraction(attraction.id))
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(attraction.icon)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
        )
        Text(
            text = attraction.name,
            style = semiboldTextStyle.copy(fontSize = 14.sp),
            modifier = Modifier
                .padding(top = 182.dp)
                .padding(horizontal = 7.dp)
                .fillMaxWidth(),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = Color(0xFF4779D8)
            )

            Text(
                text = attraction.distance,
                style = mediumTextStyle.copy(
                    color = Color(0xFF535353),
                    fontSize = 13.sp
                ),
            )
        }

    }
}
