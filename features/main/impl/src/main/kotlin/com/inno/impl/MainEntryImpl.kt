package com.inno.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.common.Destinations
import com.example.multimodulepractice.main.impl.R
import com.inno.api.MainEntry
import com.inno.impl.data.models.MainTab
import com.inno.impl.ui.compose_elements.Tab
import com.inno.impl.ui.fragments.ListFragment
import com.inno.impl.ui.fragments.MapFragmentViewHolder
import com.inno.impl.ui.fragments.ProfileFragment
import javax.inject.Inject

class MainEntryImpl @Inject constructor(
    private val mapFragmentViewHolder: MapFragmentViewHolder,
) : MainEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
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
                    .clip(RoundedCornerShape(15.dp, 15.dp,  0.dp, 0.dp))
                    .align(Alignment.BottomCenter),
                currentTab = currentTab
            )
        }
    }

    @Composable
    fun TopFragment(currentTab: MutableState<MainTab>) {
        Box(modifier = Modifier.padding(bottom = 56.dp)) {
            // resolve redrawing of fragment that way
            mapFragmentViewHolder.MapFragment()
            when (currentTab.value) {
                MainTab.LIST -> ListFragment()
                MainTab.PROFILE -> ProfileFragment()
                else -> Unit
            }
        }
    }

    @Composable
    fun TabBar(modifier: Modifier, currentTab: MutableState<MainTab>) {
        Row(
            modifier = modifier.background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Tab(currentTab, MainTab.MAP)
            Tab(currentTab, MainTab.LIST)
            Tab(currentTab, MainTab.PROFILE)
        }
    }


}