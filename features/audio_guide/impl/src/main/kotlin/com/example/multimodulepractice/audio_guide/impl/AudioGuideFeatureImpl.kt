package com.example.multimodulepractice.audio_guide.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.multimodulepractice.AudioGuideFeatureEntry
import javax.inject.Inject

class AudioGuideFeatureImpl @Inject constructor() : AudioGuideFeatureEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(featureRoute) {
            AudioGidScreen()
        }
    }

}

@Composable
fun AudioGidScreen() {
    Box(
        modifier = Modifier.fillMaxSize().background(color = Color.Red)
    )
}