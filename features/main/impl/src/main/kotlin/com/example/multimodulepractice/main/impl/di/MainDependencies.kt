package com.example.multimodulepractice.main.impl.di

import android.content.Context
import com.example.multimodulepractice.auth.AuthInfoManager
import com.example.multimodulepractice.common.di.AppContext
import com.example.multimodulepractice.geo.repository.GeoRepository
import com.example.multimodulepractice.main.impl.data.network.MainApi
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class MainDependencies @Inject constructor(
    @AppContext
    val context: Context,
    val geoRepository: GeoRepository,
    val mainApi: MainApi,
    val scope: CoroutineScope,
    val authInfoManager: AuthInfoManager
)
