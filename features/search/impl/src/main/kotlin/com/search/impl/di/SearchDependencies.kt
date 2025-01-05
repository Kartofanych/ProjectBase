package com.search.impl.di

import com.example.travelling.geo.repository.GeoRepository
import com.splash.api.domain.CitiesRepository
import retrofit2.Retrofit
import javax.inject.Inject

class SearchDependencies @Inject constructor(
    val citiesRepository: CitiesRepository,
    val geoRepository: GeoRepository,
    val retrofit: Retrofit
)
