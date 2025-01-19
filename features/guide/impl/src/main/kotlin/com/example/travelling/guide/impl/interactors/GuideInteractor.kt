package com.example.travelling.guide.impl.interactors

import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.networkCall
import com.example.travelling.guide.impl.data.GuideApi
import com.example.travelling.guide.impl.data.mappers.GuideMapper
import com.example.travelling.guide.impl.data.models.GuideRequestDto
import com.example.travelling.guide.impl.ui.models.GuideResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GuideInteractor @Inject constructor(
    private val api: GuideApi,
    private val mapper: GuideMapper
) {

    suspend fun guide(landmarkId: String): ResponseState<GuideResponse> {
        return withContext(Dispatchers.IO) {
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    val response = api.attractionGuide(GuideRequestDto(landmarkId))
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "guide", time)

                    ResponseState.Success(mapper.map(response))
                }, catch = { throwable ->
                    Analytics.reportNetworkError(route = "guide", throwable = throwable)
                    ResponseState.Error.Default()
                }
            )
        }
    }
}