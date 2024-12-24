package com.attraction.impl.ui.composables

import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.composables.NetworkImage
import com.example.multimodulepractice.common.composables.StarsComponent
import com.example.multimodulepractice.common.composables.touchAction
import com.example.multimodulepractice.common.data.models.local.Review
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.common.theme.regularTextStyle

@Composable
fun ReviewItem(review: Review) {
    val isFull = remember { mutableStateOf(review.text.length <= 114) }
    Column(
        Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .touchAction()
        ) {

            NetworkImage(
                url = review.icon,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.width(8.dp))

            Column {
                Text(
                    text = review.title,
                    style = mediumTextStyle.copy(fontSize = 14.sp, color = Color.Black),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = review.subtitle,
                    style = regularTextStyle.copy(fontSize = 10.sp, color = Color(0xFF535353)),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        StarsComponent(review.stars)

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .touchAction {
                    if (review.text.length > 114) {
                        isFull.value = !isFull.value
                    }
                }
        ) {

            Text(
                text = review.text,
                style = mediumTextStyle.copy(fontSize = 12.sp, color = Color(0xFF545454)),
                maxLines = if (isFull.value) 10000 else 4,
                overflow = TextOverflow.Ellipsis,
            )

            androidx.compose.animation.AnimatedVisibility(
                visible = !isFull.value,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Подробнее",
                    style = mediumTextStyle.copy(
                        fontSize = 12.sp,
                        color = Color.Black,
                        textDecoration = TextDecoration.Underline
                    ),
                    maxLines = if (isFull.value) 10000 else 4,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = review.date,
            style = mediumTextStyle.copy(
                fontSize = 10.sp,
                color = Color(0xFF737F89)
            ),
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(35.dp))
    }
}