package com.example.travelling.main.impl.data.interactors

import com.example.travelling.common.data.models.local.City
import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.networkCall
import com.example.travelling.main.impl.data.mappers.MapInfoMapper
import com.main.common.data.MainApi
import com.main.common.data.dto.MapInfoRequest
import com.main.common.data.local.map.MapInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MapInteractor @Inject constructor(
    private val api: MainApi,
    private val mapInfoMapper: MapInfoMapper,
) {

    suspend fun getMapInfo(city: City): ResponseState<MapInfoResponse> {
        return withContext(Dispatchers.IO) {
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    val response = api.getStartInfo(MapInfoRequest(city.id))
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "map_info", time)
                    return@withContext ResponseState.Success(
                        data = mapInfoMapper.mapResponse(response)
                    )
                },
                catch = { throwable ->
                    Analytics.reportNetworkError(route = "map_info", throwable)
                    return@withContext ResponseState.Error.Default()
                }
            )
        }
    }
}