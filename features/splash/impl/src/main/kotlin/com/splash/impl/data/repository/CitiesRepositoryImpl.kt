package com.splash.impl.data.repository

import com.example.multimodulepractice.common.di.AppScope
import com.splash.api.domain.CitiesRepository
import com.example.multimodulepractice.common.models.local.City
import com.example.multimodulepractice.common.models.local.GeoPoint
import com.example.multimodulepractice.common.utils.calculateDistance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@AppScope
class CitiesRepositoryImpl @Inject constructor() : CitiesRepository {

    private val _cities = MutableStateFlow<List<City>>(emptyList())

    override fun cities(): List<City> = _cities.value

    override fun citiesFlow(): Flow<List<City>> = _cities.asStateFlow()

    override suspend fun updateCities(list: List<City>) {
        _cities.emit(list)
    }

    override suspend fun loadedCity(city: City) {
        val current = _cities.value.toMutableList()
        current[city.index] = city.copy(isLoaded = true)
        _cities.emit(current)
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