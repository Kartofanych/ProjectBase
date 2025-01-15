package com.onboarding.impl.di

import com.travelling.api.UserPreferences
import javax.inject.Inject

class OnboardingDependencies @Inject constructor(
    val userPreferences: UserPreferences,
)
