package com.inno.api

import com.example.common.ComposableFeatureEntry

abstract class MainEntry : ComposableFeatureEntry {

    final override val featureRoute = "main"

    fun destination() = featureRoute

}