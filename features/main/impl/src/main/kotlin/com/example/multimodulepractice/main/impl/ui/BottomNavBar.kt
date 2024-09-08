package com.example.multimodulepractice.main.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.multimodulepractice.common.composables.TopShadow
import com.example.multimodulepractice.main.impl.data.models.MainTab
import com.example.multimodulepractice.main.impl.ui.compose_elements.Tab

@Composable
fun BottomNavBar(
    modifier: Modifier,
    navigateToMap: () -> Unit,
    navigateToList: () -> Unit,
    navigateToProfile: () -> Unit,
) {
    val currentTab = remember { mutableStateOf(MainTab.MAP) }
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TopShadow(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .height(20.dp)
                .fillMaxWidth(),
            alpha = 0.2f
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Tab(
                modifier = Modifier.weight(1f),
                tabValue = MainTab.MAP,
                currentTab = currentTab,
            ) {
                if (currentTab.value != MainTab.MAP) {
                    navigateToMap()
                    currentTab.value = MainTab.MAP
                }
            }
            Tab(
                modifier = Modifier.weight(1f),
                tabValue = MainTab.LIST,
                currentTab = currentTab,
            ) {
                if (currentTab.value != MainTab.LIST) {
                    navigateToList()
                    currentTab.value = MainTab.LIST
                }
            }
            Tab(
                modifier = Modifier.weight(1f),
                tabValue = MainTab.PROFILE,
                currentTab = currentTab,
            ) {
                if (currentTab.value != MainTab.PROFILE) {
                    navigateToProfile()
                    currentTab.value = MainTab.PROFILE
                }
            }
        }
    }
}