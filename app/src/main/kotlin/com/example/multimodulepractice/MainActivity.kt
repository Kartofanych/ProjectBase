package com.example.multimodulepractice

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.multimodulepractice.auth.models.AuthInfo
import com.example.multimodulepractice.common.navigation.find
import com.example.multimodulepractice.common.theme.MultimodulePracticeTheme
import com.example.multimodulepractice.guide.GuideEntry
import com.example.multimodulepractice.main.MainFeatureEntry
import com.example.multimodulepractice.login.LoginFeatureEntry

class MainActivity : AppCompatActivity() {

    private lateinit var appProvider: AppProvider

    private var startDestination = "login"

    private var locationPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        appProvider = (this.applicationContext as App).appProvider

        startDestination = when (appProvider.authInfoManager.authInfo()) {
            AuthInfo.Guest -> "login"
            is AuthInfo.User -> "main"
        }

        setContent {
            MultimodulePracticeTheme {
                CompositionLocalProvider(
                    LocalAppProvider provides application.appProvider
                ) {
                    NavigatorScaffold()
                }
            }
        }

        if (checkPermissions()) {
            appProvider.geoManager.start()
        } else {
            requestPermissions()
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

    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        permissionRequest.launch(locationPermissions)
    }

    private val permissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all {
                it.value
            }

            if (granted) {
                appProvider.geoManager.start()
            }
        }
}