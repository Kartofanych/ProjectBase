package com.example.multimodulepractice.main.impl.data.interactors

import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.landmark.data.LandmarkResponse
import com.example.multimodulepractice.main.impl.data.mappers.LandmarkMapper
import com.example.multimodulepractice.main.impl.data.network.MainApi
import com.example.multimodulepractice.main.impl.data.network.models.request.LandmarkRequest
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