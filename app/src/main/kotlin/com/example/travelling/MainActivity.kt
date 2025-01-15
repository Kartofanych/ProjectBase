package com.example.travelling

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.attraction.api.AttractionEntry
import com.example.multimodulepractice.BuildConfig
import com.example.travelling.common.navigation.find
import com.example.travelling.common.navigation.injectedViewModel
import com.example.travelling.common.navigation.register
import com.example.travelling.common.theme.MultimodulePracticeTheme
import com.example.travelling.common.theme.mediumTextStyle
import com.example.travelling.di.ActivityComponent
import com.example.travelling.guide.GuideEntry
import com.example.travelling.login.LoginFeatureEntry
import com.example.travelling.main.MainFeatureEntry
import com.example.travelling.main.impl.ui.map.MapScreen
import com.example.travelling.main.impl.ui.map.MapScreenEventHandler
import com.example.travelling.ui.splash.SplashViewModel
import com.filters.api.FiltersEntry
import com.onboarding.api.OnboardingEntry
import com.promo.api.PromoEntry
import com.reviews.api.ReviewsEntry
import com.search.api.SearchEntry
import com.service.api.ServiceEntry
import com.splash.api.LaunchEntry
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var appProvider: AppProvider

    private lateinit var mainActivityComponent: ActivityComponent

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

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { viewModel.isLoading.value }
        appProvider = (this.applicationContext as App).appProvider
        mainActivityComponent = appProvider.activityComponentFactory.create(this)

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
        val destinations = mainActivityComponent.destinations

        val mainFeature = destinations.find<MainFeatureEntry>()
        val loginFeature = destinations.find<LoginFeatureEntry>()
        val guideFeature = destinations.find<GuideEntry>()
        val audioGuideFeature = destinations.find<AudioGuideFeatureEntry>()
        val filtersFeature = destinations.find<FiltersEntry>()
        val splashFeature = destinations.find<LaunchEntry>()
        val serviceFeature = destinations.find<ServiceEntry>()
        val searchFeature = destinations.find<SearchEntry>()
        val attractionFeature = destinations.find<AttractionEntry>()
        val promoFeature = destinations.find<PromoEntry>()
        val reviewsFeature = destinations.find<ReviewsEntry>()
        val onboardingFeature = destinations.find<OnboardingEntry>()

        val isDebug = BuildConfig.DEBUG
        val isProduction = appProvider.appConfig.isProduction()

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = Color.White
        ) {

            Map(navController = navController)

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                NavHost(
                    navController = navController,
                    startDestination = splashFeature.featureRoute,
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
                        onboardingFeature,
                        navController = navController,
                        modifier = Modifier
                    )

                    register(
                        mainFeature,
                        navController = navController,
                        modifier = Modifier.padding(bottom = it.calculateBottomPadding())
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

                    register(
                        promoFeature,
                        navController = navController,
                        modifier = Modifier
                    )

                    register(
                        reviewsFeature,
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
    fun Map(navController: NavHostController) {
        val mapViewModel = injectedViewModel {
            appProvider.mapViewModel
        }

        val mapUiState = mapViewModel.uiStateFlow.collectAsState().value
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