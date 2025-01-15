package com.travelling.impl

import android.content.Context
import com.example.travelling.common.di.AppContext
import com.travelling.api.UserPreferences
import javax.inject.Inject

class UserPreferencesImpl @Inject constructor(
    @AppContext
    context: Context
) : UserPreferences {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(USER_PREFS_SHARED_PREFS, Context.MODE_PRIVATE)
    }

    override fun isOnboardingShown(): Boolean =
        sharedPreferences.getBoolean(ONBOARDING_SHOWN, false)

    override fun updateOnboardingShown() {
        sharedPreferences.edit().apply {
            putBoolean(ONBOARDING_SHOWN, true)
        }.apply()
    }

    private companion object {
        const val USER_PREFS_SHARED_PREFS = "APP_CONFIG"
        const val ONBOARDING_SHOWN = "IS_ONBOARDING_SHOWN"
    }
}