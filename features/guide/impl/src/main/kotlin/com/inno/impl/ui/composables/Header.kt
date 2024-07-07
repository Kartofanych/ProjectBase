package com.inno.impl.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.theme.mediumTextStyle
import com.example.multimodulepractice.login.R
import com.inno.impl.ui.GuideAction
import com.inno.impl.ui.GuideUiState

@Composable
fun Header(
    modifier: Modifier,
    onAction: (GuideAction) -> Unit,
    uiState: GuideUiState.Content,
    currentScreen: Int
) {

    Row(
        modifier = modifier
            .safeDrawingPadding()
            .padding(top = 8.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .size(44.dp)
                .background(Color(0xFFEAEAEA), RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    onAction(GuideAction.OnBackPressed)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_back),
                contentDescription = null,
                tint = Color(0xFF0F0F0F)
            )
        }

        Box(
            modifier = Modifier
                .size(44.dp)
                .background(Color(0xFFEAEAEA), RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${currentScreen + 1}/${uiState.topics.size}",
                style = mediumTextStyle.copy(
                    fontSize = 14.sp,
                    color = Color(0xFF0F0F0F)
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}
