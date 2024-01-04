package com.example.multimodulepractice.di

import androidx.compose.runtime.compositionLocalOf
import com.example.common.Destinations

interface AppProvider {
    val destinations: Destinations
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }