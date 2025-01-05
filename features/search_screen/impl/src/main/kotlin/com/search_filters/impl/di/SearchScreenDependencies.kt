package com.search_filters.impl.di

import com.example.travelling.geo.repository.GeoRepository
import com.search_filters.api.data.domain.SearchFilterRepository
import com.search_filters.impl.data.SearchApi
import com.splash.api.domain.CitiesRepository
import javax.inject.Inject

class SearchScreenDependencies @Inject constructor(
    val citiesRepository: CitiesRepository,
    val searchFilterRepository: SearchFilterRepository,
    val searchApi: SearchApi,
    val geoRepository: GeoRepository,
)
