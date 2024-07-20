package com.example.multimodulepractice.main.impl.ui.compose_elements

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.main.impl.data.local_models.list.CloseAttraction
import com.example.multimodulepractice.main.impl.ui.list.ListAction

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HorizontalAttraction(
    context: Context,
    attraction: CloseAttraction,
    onListAction: (ListAction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onListAction(ListAction.OpenAttraction(attraction.id))
            }
            .padding(top = 13.dp)
            .padding(start = 14.dp, end = 8.dp),
    ) {
        Row {
            Column(
                modifier = Modifier
                    .width(200.dp)
            ) {

                Text(
                    text = attraction.name,
                    style = semiboldTextStyle.copy(fontSize = 14.sp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = attraction.shortInfo,
                    style = mediumTextStyle.copy(fontSize = 11.sp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 10.dp)
                )

                FlowRow(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    maxLines = 1
                ) {
                    for (category in attraction.categories) {
                        CategoryChip(text = category.name, color = category.color)
                    }
                }

                Text(
                    text = "Дата основания: ${attraction.dateCreation}",
                    style = mediumTextStyle.copy(fontSize = 11.sp, color = Color(0xFF959595)),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(attraction.icon)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 13.dp)
                    .fillMaxHeight()
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
    }
}