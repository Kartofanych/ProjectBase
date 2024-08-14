package com.splash.impl.data.repository

import com.example.multimodulepractice.common.di.AppScope
import com.splash.api.domain.CitiesRepository
import com.example.multimodulepractice.common.models.local.City
import com.example.multimodulepractice.common.models.local.GeoPoint
import com.example.multimodulepractice.common.utils.calculateDistance
import javax.inject.Inject

@AppScope
class CitiesRepositoryImpl @Inject constructor() : CitiesRepository {

    private val _cities: MutableList<City> = ArrayList()

    override fun cities(): List<City> = _cities

    override fun updateCities(list: List<City>) {
        _cities.clear()
        _cities.addAll(list)
    }

    override fun loadedCity(city: City) {
        _cities[city.index] = city.copy(isLoaded = true)
    }

    override fun closestCity(pinPoint: GeoPoint): City? {
        for (city in cities().filter { !it.isLoaded }) {
            if (calculateDistance(pinPoint, city.geoPoint).toFloat() <= city.radius) {
                return city
            }
        }
        return null
    }
}