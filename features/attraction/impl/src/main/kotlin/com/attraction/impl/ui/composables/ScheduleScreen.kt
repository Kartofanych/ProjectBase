package com.attraction.impl.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attraction.impl.data.models.local.Attraction
import com.attraction.impl.ui.AttractionAction
import com.example.multimodulepractice.attraction.impl.R
import com.example.travelling.common.composables.touchAction
import com.example.travelling.common.theme.boldTextStyle
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.common.theme.regularTextStyle

@Composable
fun ScheduleScreen(
    modifier: Modifier,
    onAction: (AttractionAction) -> Unit,
    schedule: Attraction.Schedule
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 20.dp)
    ) {
        item {
            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = null,
                modifier = Modifier
                    .rotate(180f)
                    .size(26.dp)
                    .touchAction {
                        onAction(AttractionAction.ChangeScheduleVisibility)
                    },
                tint = Color(0xFF404040)
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Время работы",
                style = boldTextStyle.copy(fontSize = 28.sp, color = Color.Black),
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(Modifier.height(24.dp))
        }

        items(
            items = schedule.scheduleDays,
            key = {
                it.name
            }
        ) {
            Text(
                text = it.name,
                style = mediumTextStyle.copy(
                    fontSize = 14.sp,
                    color = Color(0xFF191919)
                ),
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = it.timing,
                style = regularTextStyle.copy(
                    fontSize = 13.sp,
                    color = Color(0xFF404040)
                ),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}