package com.inno.impl.ui.compose_elements

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.common.theme.tabTextStyle
import com.example.multimodulepractice.main.impl.R
import com.inno.impl.data.local.models.MainTab
import com.inno.impl.ui.MainFragment.Companion.LIST_TITLE
import com.inno.impl.ui.MainFragment.Companion.MAP_TITLE
import com.inno.impl.ui.MainFragment.Companion.PROFILE_TITLE

@Composable
fun Tab(
    modifier: Modifier,
    tabValue: MainTab,
    currentTab: MutableState<MainTab>,
    onTap: () -> Unit
) {
    val color by animateColorAsState(targetValue = if (currentTab.value == tabValue) Color.Black else Color.Gray)
    val imageId = when (tabValue) {
        MainTab.MAP -> R.drawable.ic_map
        MainTab.LIST -> R.drawable.ic_list
        MainTab.PROFILE -> R.drawable.ic_profile
    }
    val title = when (tabValue) {
        MainTab.MAP -> MAP_TITLE
        MainTab.LIST -> LIST_TITLE
        MainTab.PROFILE -> PROFILE_TITLE
    }
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(size = 10.dp))
            .clickable {
                onTap()
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = imageId),
                contentDescription = null,
                tint = color
            )
            Text(text = title, style = tabTextStyle.copy(color = color))
        }
    }
}