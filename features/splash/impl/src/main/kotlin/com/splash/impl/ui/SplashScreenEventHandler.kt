package com.splash.impl.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreenEventHandler(uiEvent: Flow<SplashEvent>, start: () -> Unit) {

    LaunchedEffect(Unit) {
        uiEvent.collectLatest { event ->
            when (event) {
                SplashEvent.Start -> start()
                else -> Log.d("HSHS", "SS")
            }
        }
    }
}