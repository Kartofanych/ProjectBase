package com.service.impl.ui.composables

import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.service.impl.R
import com.example.travelling.common.composables.DefaultButton
import com.example.travelling.common.composables.DefaultSeparator
import com.example.travelling.common.composables.ReviewItem
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.semiboldTextStyle
import com.service.impl.ui.ServiceAction
import com.service.impl.ui.ServiceUiState

@Composable
fun ReviewBlock(uiState: ServiceUiState, onAction: (ServiceAction) -> Unit) {
    val block =
        (uiState.state as? ServiceUiState.DataState.Content)?.service?.reviewsBlock ?: return
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = "Отзывы",
            style = semiboldTextStyle.copy(fontSize = 26.sp)
        )

        Spacer(Modifier.height(24.dp))

        RatingBlock(block, onAction)

        Spacer(Modifier.height(36.dp))

        if (uiState.isAuthorized) {
            DefaultButton(
                onClick = {
                    onAction(ServiceAction.ChangeReviewModalVisibility(true))
                },
                backgroundColor = Color(0xFFFFFFFF),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(300.dp)
                    .height(40.dp)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFF74A3FF),
                    ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_pen),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color(0xFF74A3FF)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Напишите отзыв",
                        style = mediumTextStyle.copy(
                            fontSize = 14.sp,
                            color = Color(0xFF74A3FF),
                        )
                    )
                }
            }

            Spacer(Modifier.height(36.dp))
        }

        if (block.reviews.isNotEmpty()) {

            for (review in block.reviews) {
                DefaultSeparator()
                Spacer(modifier = Modifier.height(30.dp))
                ReviewItem(review)
            }

            DefaultButton(
                onClick = {
                    onAction(ServiceAction.OpenReviews)
                },
                backgroundColor = Color(0xFFFFFFFF),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(300.dp)
                    .height(40.dp)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFF74A3FF),
                    ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Посмотреть все отзывы",
                    style = mediumTextStyle.copy(
                        fontSize = 14.sp,
                        color = Color(0xFF74A3FF)
                    )
                )
            }
        }

        Spacer(Modifier.height(24.dp))
    }
}
