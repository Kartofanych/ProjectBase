package com.splash.api.domain

import com.example.multimodulepractice.common.models.local.City
import com.example.multimodulepractice.common.models.local.GeoPoint

interface CitiesRepository {

    fun cities(): List<City>

    fun updateCities(list: List<City>)

    fun loadedCity(city: City)

    fun closestCity(pinPoint: GeoPoint): City?
}