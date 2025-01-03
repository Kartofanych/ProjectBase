package com.reviews.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.multimodulepractice.common.composables.StarsComponent
import com.example.multimodulepractice.common.theme.boldTextStyle
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.reviews.impl.ui.ReviewsAction
import com.reviews.impl.ui.ReviewsUiState

@Composable
fun RatingBlock(uiState: ReviewsUiState.Content, onAction: (ReviewsAction) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = uiState.ratingBlock.rating.toString(),
                style = boldTextStyle.copy(fontSize = 32.sp, color = Color(0xFF404040))
            )

            Spacer(modifier = Modifier.height(4.dp))

            StarsComponent(uiState.ratingBlock.starCount)

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = uiState.ratingBlock.reviewCount,
                style = mediumTextStyle.copy(fontSize = 10.sp, color = Color(0xFF545454))
            )
        }

        Spacer(Modifier.height(28.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            uiState.ratings.forEachIndexed { index, item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.width(75.dp),
                        text = index.toReviewText(),
                        style = mediumTextStyle.copy(fontSize = 10.sp, color = Color(0xFF545454))
                    )

                    Box(
                        Modifier
                            .width(200.dp)
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
                                            200f * (item.toFloat() / uiState.ratingBlock.total.toFloat()),
                                            3f
                                        ).dp
                                    }
                                )
                                .fillMaxHeight()
                                .background(
                                    color = Color(0xFF74A3FF),
                                    shape = CircleShape
                                )
                        )
                    }

                    Text(
                        modifier = Modifier.width(32.dp),
                        textAlign = TextAlign.End,
                        text = item.toString(),
                        style = mediumTextStyle.copy(fontSize = 10.sp, color = Color(0xFF545454))
                    )
                }
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

