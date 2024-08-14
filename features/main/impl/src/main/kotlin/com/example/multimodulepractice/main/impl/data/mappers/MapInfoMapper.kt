package com.example.multimodulepractice.main.impl.data.mappers

import com.example.multimodulepractice.common.models.network.GeoPointDto.Companion.toLocalModel
import com.example.multimodulepractice.main.impl.data.local_models.map.City
import com.example.multimodulepractice.main.impl.data.local_models.map.MapInfoResponse
import com.example.multimodulepractice.main.impl.data.local_models.map.MapLandmark
import com.example.multimodulepractice.main.impl.data.network.models.response.CityDto
import com.example.multimodulepractice.main.impl.data.network.models.response.MapInfoResponseDto
import com.example.multimodulepractice.main.impl.data.network.models.response.MapLandmarkDto
import javax.inject.Inject

class MapInfoMapper @Inject constructor(
    private val colorMapper: ColorMapper
) {

    fun mapResponse(response: MapInfoResponseDto): MapInfoResponse {
        return MapInfoResponse(
            mapCity(response.city),
            response.list.map { mapLandmark(it) }
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
}