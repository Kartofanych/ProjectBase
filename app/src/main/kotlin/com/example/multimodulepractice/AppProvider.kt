package com.example.multimodulepractice

import androidx.compose.runtime.compositionLocalOf
import com.example.multimodulepractice.auth.AuthInfoManager
import com.example.multimodulepractice.common.navigation.Destinations
import com.example.multimodulepractice.geo.GeoManager
import com.example.multimodulepractice.guide.impl.di.GuideDependencies
import com.example.multimodulepractice.main.impl.di.MainDependencies
import com.travelling.api.AppConfig

interface AppProvider {

    val geoManager: GeoManager

    val destinations: Destinations

    val authInfoManager: AuthInfoManager

    val mainDependencies: MainDependencies

    val guideDependencies: GuideDependencies

    val appConfig: AppConfig
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
