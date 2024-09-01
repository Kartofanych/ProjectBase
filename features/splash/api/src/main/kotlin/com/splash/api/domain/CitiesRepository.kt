package com.splash.api.domain

import com.example.multimodulepractice.common.models.local.City
import com.example.multimodulepractice.common.models.local.GeoPoint
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {

    fun cities(): List<City>

    fun citiesFlow(): Flow<List<City>>

    suspend fun updateCities(list: List<City>)

    suspend fun loadedCity(city: City)

    fun closestCity(pinPoint: GeoPoint): City?
}