package com.inno.impl.di

import android.content.Context
import com.example.common.di.AppContext
import com.inno.geo.repository.GeoRepository
import com.inno.impl.data.network.MainApi
import javax.inject.Inject

class MainDependencies @Inject constructor(
    @AppContext
    val context: Context,
    val api: MainApi,
    val geoRepository: GeoRepository
)
