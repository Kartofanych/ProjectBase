package com.example.multimodulepractice

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.api.AuthInfoManager
import com.example.common.theme.MultimodulePracticeTheme
import com.inno.api.GuideEntry
import com.inno.api.LoginFeatureEntry
import com.inno.api.MainFeatureEntry
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainFeatureApi: MainFeatureEntry

    @Inject
    lateinit var loginFeatureApi: LoginFeatureEntry

    @Inject
    lateinit var guideEntry: GuideEntry

    @Inject
    lateinit var authInfoManager: AuthInfoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MultimodulePracticeTheme {
                NavigatorScaffold()
            }
        }
    }

    @Composable
    private fun NavigatorScaffold() {
        val navController = rememberNavController()
        Scaffold {
            NavHost(
                navController = navController,
                startDestination = when (authInfoManager.authInfo().token) {
                    null -> loginFeatureApi.featureRoute
                    else -> mainFeatureApi.featureRoute
                },
                modifier = Modifier
                    .background(Color.White)
                    .padding(bottom = it.calculateBottomPadding())
            ) {
                register(
                    loginFeatureApi,
                    navController = navController,
                    modifier = Modifier
                )

                register(
                    mainFeatureApi,
                    navController = navController,
                    modifier = Modifier
                )

                register(
                    guideEntry,
                    navController = navController,
                    modifier = Modifier
                )
            }
        }
    }
}