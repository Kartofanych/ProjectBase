package com.splash.impl.data.mappers

import com.example.multimodulepractice.common.models.network.GeoPointDto.Companion.toLocalModel
import com.splash.impl.data.models.dto.CityDto
import com.splash.impl.data.models.dto.LaunchResponseDto
import com.example.multimodulepractice.common.models.local.City
import com.filters.api.data.models.Filters
import com.filters.api.data.models.FiltersCategory
import com.splash.impl.data.models.local.LaunchResponse
import javax.inject.Inject

class LaunchMapper @Inject constructor() {

    fun map(response: LaunchResponseDto): LaunchResponse {
        return LaunchResponse.Success(
            cities = response.cities.mapIndexed(::mapCity),
            filters = mapFilters(response.filters)
        )
    }

    private fun mapCity(index: Int, dto: CityDto): City {
        return City(
            id = dto.id,
            name = dto.name,
            radius = dto.radius,
            geoPoint = dto.geoPoint.toLocalModel(),
            isLoaded = false,
            index = index,
            icon = dto.icon
        )
    }

    private fun mapFilters(filtersDto: com.splash.impl.data.models.dto.FiltersDto): Filters {
        return Filters(
            categories = filtersDto.categories.mapIndexed { index, item ->
                FiltersCategory(
                    item.id,
                    index,
                    item.name,
                    3,
                    item.isDefault
                )
            }
        )
    }
}
