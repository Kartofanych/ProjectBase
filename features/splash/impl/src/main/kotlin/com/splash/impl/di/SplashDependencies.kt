package com.splash.impl.di

import android.content.Context
import com.example.multimodulepractice.auth.AuthInfoManager
import com.example.multimodulepractice.common.di.AppContext
import com.filters.api.data.FiltersRepository
import com.splash.api.domain.CitiesRepository
import com.splash.impl.data.LaunchApi
import javax.inject.Inject

class SplashDependencies @Inject constructor(
    @AppContext
    val context: Context,
    val launchApi: LaunchApi,
    val authInfoManager: AuthInfoManager,
    val citiesRepository: CitiesRepository,
    val filtersRepository: FiltersRepository
)
