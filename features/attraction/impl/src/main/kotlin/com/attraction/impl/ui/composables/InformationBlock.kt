package com.attraction.impl.ui.composables

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attraction.impl.data.models.local.Attraction
import com.attraction.impl.ui.AttractionAction
import com.example.multimodulepractice.attraction.impl.R
import com.example.travelling.common.composables.DefaultButton
import com.example.travelling.common.composables.ReviewStarsComponent
import com.example.travelling.common.composables.touchAction
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.regularTextStyle
import com.example.travelling.common.theme.semiboldTextStyle

@Composable
fun InformationBlock(attraction: Attraction, onAction: (AttractionAction) -> Unit) {
    val block = attraction.infoBlock
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = block.name,
            style = semiboldTextStyle.copy(fontSize = 26.sp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            ReviewStarsComponent(
                block.ratingBlock.rating.toString(),
                block.ratingBlock.starCount,
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = block.ratingBlock.reviewCount,
                style = regularTextStyle.copy(
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFF74A3FF)
                ),
                modifier = Modifier.touchAction {
                    onAction(AttractionAction.OpenReviews)
                }
            )
        }

        if (attraction.isAuthorized) {

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Написать отзыв",
                style = semiboldTextStyle.copy(
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFF535353)
                ),
                modifier = Modifier.touchAction {
                    onAction(AttractionAction.ChangeReviewModalVisibility(true))
                }
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier
                .padding(start = 6.dp, end = 16.dp)
                .fillMaxWidth()
                .touchAction {
                    onAction(AttractionAction.ChangeScheduleVisibility)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = block.scheduleStatus.status,
                    style = semiboldTextStyle.copy(
                        fontSize = 15.sp,
                        color = Color(0xFF535353)
                    )
                )

                Text(
                    text = block.scheduleStatus.timing,
                    style = mediumTextStyle.copy(
                        fontSize = 11.sp,
                        color = Color(0xFF535353)
                    )
                )
            }

            Icon(
                painter = painterResource(R.drawable.ic_arrow),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier
                .padding(start = 6.dp, end = 16.dp)
                .fillMaxWidth()
                .touchAction {
                    onAction(AttractionAction.OpenOnMap)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = "Как добраться?",
                    style = semiboldTextStyle.copy(
                        fontSize = 15.sp,
                        color = Color(0xFF535353)
                    )
                )

                Text(
                    text = block.address,
                    style = mediumTextStyle.copy(
                        fontSize = 11.sp,
                        color = Color(0xFF535353)
                    )
                )
            }

            Icon(
                painter = painterResource(R.drawable.ic_geo),
                contentDescription = null,
                modifier = Modifier.size(14.dp)
            )
        }

        if (block.showGuide) {

            Spacer(modifier = Modifier.height(35.dp))

            DefaultButton(
                onClick = {
                    onAction(AttractionAction.OpenGuide)
                },
                backgroundColor = Color(0xFF74A3FF),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(300.dp)
                    .height(40.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_info),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    Text(
                        text = "Читать подробнее",
                        style = mediumTextStyle.copy(
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(35.dp))
    }
}
