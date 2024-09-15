package com.attraction.impl.domain

import com.attraction.impl.data.AttractionApi
import com.attraction.impl.data.mappers.AttractionMapper
import com.attraction.impl.data.models.dto.LandmarkRequest
import com.attraction.impl.data.models.local.Attraction
import com.example.multimodulepractice.common.data.models.local.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AttractionInteractor @Inject constructor(
    private val api: AttractionApi,
    private val attractionMapper: AttractionMapper
) {

    suspend fun getLandmarkInfo(id: String): ResponseState<Attraction> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getLandmark(LandmarkRequest(id = id))
                return@withContext ResponseState.Success(data = attractionMapper.mapResponse(response))
            } catch (exception: Exception) {
                return@withContext ResponseState.Error.Default()
            }
        }
    }
}