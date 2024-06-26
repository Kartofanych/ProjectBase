package com.inno.impl.data.interactors

import com.example.common.models.local.GeoPoint.Companion.toDto
import com.example.common.models.local.ResponseState
import com.inno.geo.repository.GeoRepository
import com.inno.landmark.data.LandmarkResponse
import com.inno.impl.data.local_models.map.MapInfoResponse
import com.inno.impl.data.mappers.LandmarkMapper
import com.inno.impl.data.mappers.MapInfoMapper
import com.inno.impl.data.network.MainApi
import com.inno.impl.data.network.models.request.LandmarkRequest
import com.inno.impl.data.network.models.request.StartInfoRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MapInteractor @Inject constructor(
    private val api: MainApi,
    private val geoRepository: GeoRepository,
    private val mapInfoMapper: MapInfoMapper,
    private val landmarkMapper: LandmarkMapper
) {

    suspend fun getMapInfo(): ResponseState<MapInfoResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val geoPoint = geoRepository.geoInfo().currentPoint.toDto()
                val response = api.getStartInfo(
                    StartInfoRequest(
                        geoPoint = geoPoint
                    )
                )
                return@withContext ResponseState.Success(data = mapInfoMapper.mapResponse(response))
            } catch (exception: Exception) {
                return@withContext ResponseState.Error()
            }
        }
    }

    suspend fun getLandmarkInfo(id: String): ResponseState<LandmarkResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getLandmark(LandmarkRequest(id = id))
                return@withContext ResponseState.Success(data = landmarkMapper.mapResponse(response))
            } catch (exception: Exception) {
                return@withContext ResponseState.Error()
            }
        }
    }

}