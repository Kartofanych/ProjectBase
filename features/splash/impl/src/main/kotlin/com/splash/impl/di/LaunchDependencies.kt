package com.splash.impl.di

import android.content.Context
import com.example.travelling.auth.AuthInfoManager
import com.example.travelling.common.di.AppContext
import com.example.travelling.common.domain.DeeplinkHandler
import com.filters.api.data.FiltersRepository
import com.splash.api.domain.CitiesRepository
import com.splash.impl.data.LaunchApi
import com.travelling.api.UserPreferences
import javax.inject.Inject

class LaunchDependencies @Inject constructor(
    @AppContext
    val context: Context,
    val launchApi: LaunchApi,
    val authInfoManager: AuthInfoManager,
    val userPreferences: UserPreferences,
    val citiesRepository: CitiesRepository,
    val filtersRepository: FiltersRepository,
    val deeplinkHandler: DeeplinkHandler,
)
