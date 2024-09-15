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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.attraction.api.AttractionEntry
import com.example.multimodulepractice.common.navigation.find
import com.example.multimodulepractice.common.navigation.injectedViewModel
import com.example.multimodulepractice.common.navigation.register
import com.example.multimodulepractice.common.theme.MultimodulePracticeTheme
import com.example.multimodulepractice.common.theme.mediumTextStyle
import com.example.multimodulepractice.guide.GuideEntry
import com.example.multimodulepractice.login.LoginFeatureEntry
import com.filters.api.FiltersEntry
import com.example.multimodulepractice.main.MainFeatureEntry
import com.example.multimodulepractice.main.impl.ui.map.MapScreen
import com.example.multimodulepractice.main.impl.ui.map.MapScreenEventHandler
import com.search.api.SearchEntry
import com.service.api.ServiceEntry
import com.splash.api.SplashEntry
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var appProvider: AppProvider

    private var locationPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private val permissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all {
                it.value
            }

            if (granted) {
                appProvider.geoManager.start()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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
        val filtersFeature = destinations.find<FiltersEntry>()
        val splashFeature = destinations.find<SplashEntry>()
        val serviceFeature = destinations.find<ServiceEntry>()
        val searchFeature = destinations.find<SearchEntry>()
        val attractionFeature = destinations.find<AttractionEntry>()

        val isDebug = BuildConfig.DEBUG
        val isProduction = appProvider.appConfig.isProduction()


        Scaffold(containerColor = Color.Transparent) {

            Map(
                modifier = Modifier
                    .padding(bottom = it.calculateBottomPadding() + 52.dp)
                    .fillMaxSize(),
                navController = navController
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                NavHost(
                    navController = navController,
                    startDestination = splashFeature.featureRoute,
                    modifier = Modifier.padding(bottom = it.calculateBottomPadding())
                ) {
                    register(
                        splashFeature,
                        navController = navController,
                        modifier = Modifier
                    )

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

                    register(
                        filtersFeature,
                        navController = navController,
                        modifier = Modifier
                    )

                    register(
                        serviceFeature,
                        navController = navController,
                        modifier = Modifier
                    )

                    register(
                        searchFeature,
                        navController = navController,
                        modifier = Modifier
                    )

                    register(
                        attractionFeature,
                        navController = navController,
                        modifier = Modifier
                    )
                }
                if (isDebug) {
                    DebugPanel(
                        modifier = Modifier
                            .align(Alignment.CenterEnd),
                        isProduction = isProduction
                    )
                }
            }
        }
    }

    @Composable
    fun Map(navController: NavHostController, modifier: Modifier) {
        val mapViewModel = injectedViewModel {
            appProvider.mapViewModel
        }

        val mapUiState = mapViewModel.uiStateFlow.collectAsState().value
        Box(
            modifier = modifier
        ) {
            MapScreenEventHandler(
                uiEvent = mapViewModel.uiEvent,
                openFilters = { navController.navigate("filters") },
                openAttraction = { id -> navController.navigate("attraction/$id") },
            )
            MapScreen(
                uiState = mapUiState,
                onMapAction = mapViewModel::onMapAction,
                map = mapViewModel.map
            )
        }
    }

    @Composable
    fun DebugPanel(modifier: Modifier, isProduction: Boolean) {
        val activeColor = Color(0xFFEBEBEB)
        val passiveColor = Color(0xFFCCCCCC)
        Column(
            modifier = modifier
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .size(50.dp)
                    .background(
                        color = if (!isProduction) activeColor else passiveColor,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable(enabled = !isProduction) {
                        appProvider.appConfig.updateMode(isProduction = true)
                        restartApp()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = PRODUCTION_TITLE,
                    style = mediumTextStyle.copy(fontSize = 10.sp)
                )
            }
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .size(50.dp)
                    .background(
                        color = if (!isProduction) passiveColor else activeColor,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable(enabled = isProduction) {
                        appProvider.appConfig.updateMode(isProduction = false)
                        restartApp()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = TESTING_TITLE,
                    style = mediumTextStyle.copy(fontSize = 10.sp)
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

    private fun restartApp() {
        lifecycleScope.launch {
            delay(100)
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            finishAffinity()
            startActivity(intent)
            exitProcess(0)
        }
    }

    private companion object {
        const val PRODUCTION_TITLE = "Prod"
        const val TESTING_TITLE = "Testing"
    }
}