package com.inno.impl.interactors

import com.example.common.models.local.ResponseState
import com.inno.impl.data.GuideApi
import com.inno.impl.data.mappers.GuideMapper
import com.inno.impl.data.models.GuideRequestDto
import com.inno.impl.ui.models.GuideResponse
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
                ResponseState.Error()
            }
        }
    }

}