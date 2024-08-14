package com.example.multimodulepractice.main.impl.data.interactors

import com.example.multimodulepractice.common.models.local.City
import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.main.impl.data.local_models.map.MapInfoResponse
import com.example.multimodulepractice.main.impl.data.mappers.MapInfoMapper
import com.example.multimodulepractice.main.impl.data.network.MainApi
import com.example.multimodulepractice.main.impl.data.network.models.request.MapInfoRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MapInteractor @Inject constructor(
    private val api: MainApi,
    private val mapInfoMapper: MapInfoMapper,
) {

    suspend fun getMapInfo(city: City): ResponseState<MapInfoResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getStartInfo(MapInfoRequest(city.id))
                return@withContext ResponseState.Success(data = mapInfoMapper.mapResponse(response))
            } catch (exception: Exception) {
                return@withContext ResponseState.Error()
            }
        }
    }
}