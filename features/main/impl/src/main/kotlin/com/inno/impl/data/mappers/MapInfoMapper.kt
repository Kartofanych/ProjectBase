package com.inno.impl.data.mappers

import com.example.common.models.network.GeoPointDto.Companion.toLocalModel
import com.inno.impl.data.local.models.City
import com.inno.impl.data.local.models.MapInfoResponse
import com.inno.impl.data.local.models.MapLandmark
import com.inno.impl.data.network.models.response.CityDto
import com.inno.impl.data.network.models.response.MapInfoResponseDto
import com.inno.impl.data.network.models.response.MapLandmarkDto
import javax.inject.Inject

class MapInfoMapper @Inject constructor() {

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
            landmarkDto.icon
        )
    }

}