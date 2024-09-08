package com.example.multimodulepractice.main.impl.data.mappers

import com.example.multimodulepractice.common.models.network.GeoPointDto.Companion.toLocalModel
import com.main.common.data.local.map.City
import com.main.common.data.local.map.MapInfoResponse
import com.main.common.data.local.map.MapLandmark
import com.main.common.data.dto.CityDto
import com.main.common.data.dto.MapInfoResponseDto
import com.main.common.data.dto.MapLandmarkDto
import com.main.common.domain.ColorMapper
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