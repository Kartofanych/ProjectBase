package com.search.impl.di

import com.example.multimodulepractice.geo.repository.GeoRepository
import com.search.impl.data.SearchApi
import com.splash.api.domain.CitiesRepository
import javax.inject.Inject

class SearchDependencies @Inject constructor(
    val citiesRepository: CitiesRepository,
    val searchApi: SearchApi,
    val geoRepository: GeoRepository
)
