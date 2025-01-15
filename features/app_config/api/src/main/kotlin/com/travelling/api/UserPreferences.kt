package com.travelling.api

interface UserPreferences {

    fun isOnboardingShown(): Boolean

    fun updateOnboardingShown()
}