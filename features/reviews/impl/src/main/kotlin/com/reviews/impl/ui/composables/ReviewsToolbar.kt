package com.reviews.impl.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.common.theme.semiboldTextStyle
import com.example.multimodulepractice.reviews.impl.R
import com.reviews.impl.ui.ReviewsAction

@Composable
fun ReviewsToolbar(
    title: String,
    onAction: (ReviewsAction) -> Unit,
    padding: Dp
) {

    Box(
        modifier = Modifier
            .padding(top = padding + 10.dp)
            .fillMaxWidth(),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .rotate(180f)
                .size(44.dp)
                .clip(CircleShape)
                .clickable {
                    onAction(ReviewsAction.OnBackPressed)
                }
                .padding(8.dp)
        )

        Text(
            text = title,
            style = semiboldTextStyle.copy(fontSize = 14.sp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .widthIn(max = 260.dp)
        )
    }
}
