package com.example.multimodulepractice

import androidx.compose.runtime.compositionLocalOf
import com.attraction.impl.di.AttractionDependencies
import com.example.multimodulepractice.auth.AuthInfoManager
import com.example.multimodulepractice.common.navigation.Destinations
import com.example.multimodulepractice.geo.GeoManager
import com.example.multimodulepractice.guide.impl.di.GuideDependencies
import com.example.multimodulepractice.main.impl.di.MainDependencies
import com.example.multimodulepractice.main.impl.ui.map.MapViewModel
import com.filters.impl.di.FiltersDependencies
import com.search.impl.di.SearchDependencies
import com.travelling.api.AppConfig

interface AppProvider {

    val geoManager: GeoManager

    val destinations: Destinations

    val authInfoManager: AuthInfoManager

    val mainDependencies: MainDependencies

    val mapViewModel: MapViewModel

    val guideDependencies: GuideDependencies

    val appConfig: AppConfig

    val filtersDependencies: FiltersDependencies

    val searchDependencies: SearchDependencies

    val attractionDependencies: AttractionDependencies
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
