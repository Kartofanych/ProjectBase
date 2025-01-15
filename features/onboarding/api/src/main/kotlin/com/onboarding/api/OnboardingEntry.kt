package com.onboarding.api

import com.example.travelling.common.navigation.FeatureEntry

abstract class OnboardingEntry : FeatureEntry {

    override val featureRoute = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "onboarding"
    }
}
