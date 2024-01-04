package com.example.api

import com.example.common.ComposableFeatureEntry

abstract class AuthEntry : ComposableFeatureEntry {

    final override val featureRoute = "auth"

    fun destination() = featureRoute

}
