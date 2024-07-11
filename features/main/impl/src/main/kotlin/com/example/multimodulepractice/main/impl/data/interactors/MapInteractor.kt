package com.example.multimodulepractice.main.impl.data.interactors

import com.example.multimodulepractice.common.models.local.GeoPoint.Companion.toDto
import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.geo.repository.GeoRepository
import com.example.multimodulepractice.main.impl.data.local_models.map.MapInfoResponse
import com.example.multimodulepractice.main.impl.data.mappers.MapInfoMapper
import com.example.multimodulepractice.main.impl.data.network.MainApi
import com.example.multimodulepractice.main.impl.data.network.models.request.StartInfoRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MapInteractor @Inject constructor(
    private val api: MainApi,
    private val geoRepository: GeoRepository,
    private val mapInfoMapper: MapInfoMapper
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

}