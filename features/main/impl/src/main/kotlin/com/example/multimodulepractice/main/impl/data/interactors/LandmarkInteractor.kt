package com.example.multimodulepractice.main.impl.data.interactors

import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.main.impl.data.mappers.LandmarkMapperImpl
import com.main.common.data.MainApi
import com.main.common.data.dto.LandmarkRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LandmarkInteractor @Inject constructor(
    private val api: MainApi,
    private val landmarkMapper: LandmarkMapperImpl
) {

    suspend fun getLandmarkInfo(id: String): ResponseState<com.main.common.data.local.LandmarkResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getLandmark(LandmarkRequest(id = id))
                return@withContext ResponseState.Success(data = landmarkMapper.mapResponse(response))
            } catch (exception: Exception) {
                return@withContext ResponseState.Error(exception)
            }
        }
    }
}