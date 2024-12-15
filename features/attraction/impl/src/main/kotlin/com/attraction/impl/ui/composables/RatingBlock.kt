package com.attraction.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attraction.impl.data.models.local.Attraction
import com.attraction.impl.ui.AttractionAction
import com.example.multimodulepractice.common.composables.StarsComponent
import com.example.multimodulepractice.common.theme.boldTextStyle
import com.example.multimodulepractice.common.theme.mediumTextStyle

@Composable
fun RatingBlock(block: Attraction.ReviewBlock, onAction: (AttractionAction) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = block.ratingBlock.rating.toString(),
                style = boldTextStyle.copy(fontSize = 32.sp, color = Color(0xFF404040))
            )

            Spacer(modifier = Modifier.height(4.dp))

            StarsComponent(block.ratingBlock.starCount)

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = block.ratingBlock.reviewCount,
                style = mediumTextStyle.copy(fontSize = 10.sp, color = Color(0xFF545454))
            )
        }

        Column {
            block.reviewCounts.forEachIndexed { index, item ->
                Row(
                    modifier = Modifier.width(194.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.width(72.dp),
                        text = index.toReviewText(),
                        style = mediumTextStyle.copy(fontSize = 10.sp, color = Color(0xFF545454))
                    )

                    Box(
                        Modifier
                            .width(80.dp)
                            .height(8.dp)
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = CircleShape
                            )
                    ) {
                        Box(
                            Modifier
                                .width(
                                    when (item.toFloat()) {
                                        0f -> 0.dp
                                        else -> maxOf(
                                            80 * (item.toFloat() / block.reviewsNumber.toFloat()),
                                            3f
                                        ).dp
                                    }
                                )
                                .height(8.dp)
                                .background(
                                    color = Color(0xFF74A3FF),
                                    shape = CircleShape
                                )
                        )
                    }

                    Text(
                        modifier = Modifier.width(42.dp),
                        textAlign = TextAlign.End,
                        text = item.toString(),
                        style = mediumTextStyle.copy(fontSize = 10.sp, color = Color(0xFF545454))
                    )
                }

                Spacer(Modifier.height(2.dp))
            }
        }
    }
}

private fun Int.toReviewText(): String {
    return when (this) {
        0 -> "Отлично"
        1 -> "Хорошо"
        2 -> "Нормально"
        3 -> "Плохо"
        else -> "Ужасно"
    }
}
