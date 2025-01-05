package com.service.impl.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.service.impl.R
import com.example.travelling.common.theme.semiboldTextStyle
import com.example.travelling.common.utils.dpToPx
import com.example.travelling.common.utils.screenWidthPx
import com.service.impl.data.models.local.Service
import com.service.impl.ui.ServiceAction

@Composable
fun ServiceToolbar(
    service: Service,
    scrollState: ScrollState,
    onAction: (ServiceAction) -> Unit
) {

    val context = LocalContext.current
    val scrollMoment = context.screenWidthPx() / 1.8f + context.dpToPx(50f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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
                    onAction(ServiceAction.OnBackPressed)
                }
                .padding(8.dp)
        )

        AnimatedVisibility(
            visible = scrollState.value > scrollMoment,
            modifier = Modifier
                .align(Alignment.Center)
                .width(250.dp),
            enter = slideInVertically(animationSpec = tween(durationMillis = 50)) { it / 2 },
            exit = slideOutVertically(animationSpec = tween(durationMillis = 50)) { it / 2 },
        ) {
            Text(
                text = service.title,
                style = semiboldTextStyle.copy(fontSize = 14.sp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(27.dp)
        )
    }
}
