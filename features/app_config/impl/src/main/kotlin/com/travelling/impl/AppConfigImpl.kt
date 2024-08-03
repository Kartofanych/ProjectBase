package com.travelling.impl

import android.content.Context
import com.example.multimodulepractice.common.di.AppContext
import com.travelling.api.AppConfig
import javax.inject.Inject

class AppConfigImpl @Inject constructor(
    @AppContext
    context: Context
) : AppConfig {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(APP_CONFIG_SHARED_PREFS, Context.MODE_PRIVATE)
    }

    override fun isProduction(): Boolean =
        sharedPreferences.getBoolean(APP_CONFIG_IS_PRODUCTION, true)

    override fun updateMode(isProduction: Boolean) {
        sharedPreferences.edit().apply {
            putBoolean(APP_CONFIG_IS_PRODUCTION, isProduction)
        }.apply()
    }

    private companion object {
        const val APP_CONFIG_SHARED_PREFS = "APP_CONFIG"
        const val APP_CONFIG_IS_PRODUCTION = "IS_PRODUCTION"
    }
}