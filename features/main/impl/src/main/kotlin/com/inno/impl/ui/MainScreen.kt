package com.inno.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inno.impl.data.models.MainTab
import com.inno.impl.ui.compose_elements.Tab
import com.inno.impl.ui.fragments.ListFragment
import com.inno.impl.ui.fragments.MapFragment
import com.inno.impl.ui.fragments.ProfileFragment

@Composable
fun MainScreen() {
    val currentTab = rememberSaveable { mutableStateOf(MainTab.MAP) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopFragment(currentTab)
        TabBar(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .background(Color.Black),
            currentTab = currentTab
        )
    }
}

@Composable
fun TopFragment(currentTab: MutableState<MainTab>) {
    Box(modifier = Modifier.padding(bottom = 56.dp)) {
        when (currentTab.value) {
            MainTab.MAP -> MapFragment()
            MainTab.LIST -> ListFragment()
            MainTab.PROFILE -> ProfileFragment()
        }
    }
}

@Composable
fun TabBar(modifier: Modifier, currentTab: MutableState<MainTab>) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Tab(currentTab, MainTab.MAP)
        Tab(currentTab, MainTab.LIST)
        Tab(currentTab, MainTab.PROFILE)
    }
}

@Preview
@Composable
fun Preview() {
    MainScreen()
}
