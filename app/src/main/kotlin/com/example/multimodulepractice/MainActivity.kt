package com.example.multimodulepractice

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.multimodulepractice.common.navigation.find
import com.example.multimodulepractice.common.theme.MultimodulePracticeTheme
import com.example.multimodulepractice.guide.GuideEntry
import com.example.multimodulepractice.main.MainFeatureEntry
import com.example.multimodulepractice.login.LoginFeatureEntry

class MainActivity : AppCompatActivity() {

    private lateinit var appProvider: AppProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        appProvider = (this.applicationContext as App).appProvider
        setContent {
            MultimodulePracticeTheme {
                CompositionLocalProvider(
                    LocalAppProvider provides application.appProvider
                ) {
                    NavigatorScaffold()
                }
            }
        }
    }

    @Composable
    private fun NavigatorScaffold() {
        val navController = rememberNavController()
        val destinations = LocalAppProvider.current.destinations

        val mainFeature = destinations.find<MainFeatureEntry>()
        val loginFeature = destinations.find<LoginFeatureEntry>()
        val guideFeature = destinations.find<GuideEntry>()
        val audioGuideFeature = destinations.find<AudioGuideFeatureEntry>()
        val startDestination = when (appProvider.authInfoManager.authInfo().token) {
            "null" -> loginFeature.featureRoute
            else -> mainFeature.featureRoute
        }

        Scaffold {
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier
                    .background(Color.White)
                    .padding(bottom = it.calculateBottomPadding())
            ) {
                register(
                    loginFeature,
                    navController = navController,
                    modifier = Modifier
                )

                register(
                    mainFeature,
                    navController = navController,
                    modifier = Modifier
                )

                register(
                    guideFeature,
                    navController = navController,
                    modifier = Modifier
                )

                register(
                    audioGuideFeature,
                    navController = navController,
                    modifier = Modifier
                )
            }
        }
    }
}