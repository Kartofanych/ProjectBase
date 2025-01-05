package com.example.travelling.audio_guide.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.travelling.AudioGuideFeatureEntry
import com.example.travelling.audio_guide.impl.ui.AudioGidScreen
import javax.inject.Inject

class AudioGuideFeatureImpl @Inject constructor() : AudioGuideFeatureEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        mainNavController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(featureRoute) {
            AudioGidScreen()
        }
    }
}
