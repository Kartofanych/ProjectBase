package com.example.travelling.main.impl.ui.compose_elements

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.multimodulepractice.main.impl.R
import com.example.travelling.common.composables.touchAction
import com.example.travelling.common.theme.tabTextStyle
import com.example.travelling.main.impl.data.models.MainTab
import com.example.travelling.main.impl.ui.MainAction
import com.example.travelling.main.impl.ui.MainUiState

@Composable
fun Tab(
    modifier: Modifier,
    tabValue: MainTab,
    uiState: MainUiState,
    onAction: (MainAction) -> Unit
) {
    val color by animateColorAsState(targetValue = if (uiState.currentTab == tabValue) Color.Black else Color.Gray)
    val imageId = when (tabValue) {
        MainTab.MAP -> R.drawable.ic_map
        MainTab.LIST -> R.drawable.ic_list
        MainTab.FAVOURITES -> R.drawable.ic_favourites
    }
    val title = tabValue.title
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(size = 10.dp))
            .touchAction {
                onAction(MainAction.OpenTab(tabValue))
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = imageId),
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(30.dp)
            )
            Text(text = title, style = tabTextStyle.copy(color = color))
        }
    }
}