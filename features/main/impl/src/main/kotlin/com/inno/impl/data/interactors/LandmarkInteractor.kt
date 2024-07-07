package com.inno.impl.data.interactors

import com.example.common.models.local.ResponseState
import com.inno.impl.data.mappers.LandmarkMapper
import com.inno.impl.data.network.MainApi
import com.inno.impl.data.network.models.request.LandmarkRequest
import com.inno.landmark.data.LandmarkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LandmarkInteractor @Inject constructor(
    private val api: MainApi,
    private val landmarkMapper: LandmarkMapper
) {

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