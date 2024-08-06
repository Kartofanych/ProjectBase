package com.example.multimodulepractice.main.impl.data.mappers

import com.example.multimodulepractice.common.models.network.GeoPointDto.Companion.toLocalModel
import com.example.multimodulepractice.main.impl.data.local_models.map.City
import com.example.multimodulepractice.main.impl.data.local_models.map.MapInfoResponse
import com.example.multimodulepractice.main.impl.data.local_models.map.MapLandmark
import com.example.multimodulepractice.main.impl.data.network.models.response.CityDto
import com.example.multimodulepractice.main.impl.data.network.models.response.FiltersDto
import com.example.multimodulepractice.main.impl.data.network.models.response.MapInfoResponseDto
import com.example.multimodulepractice.main.impl.data.network.models.response.MapLandmarkDto
import com.filters.api.data.models.Filters
import com.filters.api.data.models.FiltersCategory
import javax.inject.Inject

class MapInfoMapper @Inject constructor(
    private val colorMapper: ColorMapper
) {

    fun mapResponse(response: MapInfoResponseDto): MapInfoResponse {
        return MapInfoResponse(
            mapCity(response.city),
            response.list.map { mapLandmark(it) },
            mapFilters(response.filters)
        )
    }

    private fun mapCity(city: CityDto): City {
        return City(
            name = city.name,
            points = city.points.map { it.toLocalModel() }
        )
    }

    private fun mapLandmark(landmarkDto: MapLandmarkDto): MapLandmark {
        return MapLandmark(
            landmarkDto.id,
            landmarkDto.name,
            landmarkDto.geoPoint.toLocalModel(),
            landmarkDto.icon,
            colorMapper.mapColorToInt(landmarkDto.color),
            landmarkDto.distance,
            landmarkDto.categoryIds
        )
    }

    private fun mapFilters(filtersDto: FiltersDto): Filters {
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