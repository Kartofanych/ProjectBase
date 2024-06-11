package com.inno.impl.ui.compose_elements

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.multimodulepractice.main.impl.R
import com.inno.impl.data.models.MainTab

@Composable
fun Tab(currentTab: MutableState<MainTab>, tabValue: MainTab) {
    val color by animateColorAsState(targetValue = if (currentTab.value == tabValue) Color.Black else Color.Gray)
    val imageId = when(tabValue){
        MainTab.MAP -> R.drawable.map
        MainTab.LIST -> R.drawable.list
        MainTab.PROFILE -> R.drawable.profile
    }
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(56.dp)
            .clickable {
                currentTab.value = tabValue
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = imageId),
            contentDescription = null,
            tint = color
        )
    }
}