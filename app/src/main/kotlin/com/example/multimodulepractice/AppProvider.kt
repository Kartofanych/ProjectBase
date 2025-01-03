package com.example.multimodulepractice

import androidx.compose.runtime.compositionLocalOf
import com.attraction.impl.di.AttractionDependencies
import com.example.multimodulepractice.auth.AuthInfoManager
import com.example.multimodulepractice.di.ActivityComponent
import com.example.multimodulepractice.geo.GeoManager
import com.example.multimodulepractice.guide.impl.di.GuideDependencies
import com.example.multimodulepractice.main.impl.di.MainDependencies
import com.example.multimodulepractice.main.impl.ui.map.MapViewModel
import com.filters.impl.di.FiltersDependencies
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

    val attractionDependencies: AttractionDependencies

    val reviewsDependencies: ReviewsDependencies
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
