package com.example.multimodulepractice.audio_guide.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.multimodulepractice.AudioGuideFeatureEntry
import com.example.multimodulepractice.audio_guide.impl.ui.AudioGidScreen
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
