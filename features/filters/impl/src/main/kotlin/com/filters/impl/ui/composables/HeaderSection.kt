package com.filters.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multimodulepractice.filters.impl.R
import com.example.travelling.common.composables.touchAction
import com.example.travelling.common.theme.mediumTextStyle
import com.filters.impl.ui.FiltersAction

@Composable
fun HeaderSection(onAction: (FiltersAction) -> Unit) {
    Box(
        modifier = Modifier
            .padding(start = 18.dp, top = 20.dp, end = 18.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .height(30.dp)
                .wrapContentWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .touchAction {
                    onAction(FiltersAction.OnZeroFilters)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Сбросить",
                style = mediumTextStyle.copy(fontSize = 14.sp, color = Color(0xFF85889E))
            )
        }

        Text(
            text = "Фильтры",
            style = mediumTextStyle.copy(fontSize = 18.sp, color = Color.Black),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .height(30.dp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(34.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .touchAction {
                    onAction(FiltersAction.OnClose(false))
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                tint = Color(0xFF000000)
            )
        }
    }
}