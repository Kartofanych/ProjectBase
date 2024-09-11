package com.example.multimodulepractice.guide.impl.interactors

import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.guide.impl.data.GuideApi
import com.example.multimodulepractice.guide.impl.data.mappers.GuideMapper
import com.example.multimodulepractice.guide.impl.data.models.GuideRequestDto
import com.example.multimodulepractice.guide.impl.ui.models.GuideResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GuideInteractor @Inject constructor(
    private val api: GuideApi,
    private val mapper: GuideMapper
) {

    suspend fun guide(landmarkId: String): ResponseState<GuideResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.attractionGuide(GuideRequestDto(landmarkId))
                ResponseState.Success(mapper.map(response))
            } catch (e: Exception) {
                ResponseState.Error.Default()
            }
        }
    }
}