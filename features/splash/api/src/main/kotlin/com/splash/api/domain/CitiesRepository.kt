package com.splash.api.domain

import com.example.travelling.common.data.models.local.City
import com.example.travelling.common.data.models.local.GeoPoint
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {

    fun cities(): List<City>

    fun citiesFlow(): Flow<List<City>>

    suspend fun updateCities(list: List<City>)

    suspend fun loadedCity(city: City)

    fun closestCity(pinPoint: GeoPoint): City?
}