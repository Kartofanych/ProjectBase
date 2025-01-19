package com.example.travelling

import androidx.compose.runtime.compositionLocalOf
import com.example.travelling.auth.AuthInfoManager
import com.example.travelling.di.ActivityComponent
import com.example.travelling.geo.GeoManager
import com.example.travelling.guide.impl.di.GuideDependencies
import com.example.travelling.main.impl.di.MainDependencies
import com.example.travelling.main.impl.ui.map.MapViewModel
import com.filters.impl.di.FiltersDependencies
import com.onboarding.impl.di.OnboardingDependencies
import com.promo.impl.di.PromoDependencies
import com.reviews.impl.di.ReviewsDependencies
import com.travelling.api.AppConfig

interface AppProvider {

    val activityComponentFactory: ActivityComponent.Factory

    val geoManager: GeoManager

    val authInfoManager: AuthInfoManager

    val mainDependencies: MainDependencies

    val mapViewModel: MapViewModel

    val guideDependencies: GuideDependencies

    val appConfig: AppConfig

    val filtersDependencies: FiltersDependencies

    val reviewsDependencies: ReviewsDependencies

    val promoDependencies: PromoDependencies

    val onboardingDependencies: OnboardingDependencies
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
