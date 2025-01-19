package com.attraction.impl.domain

import com.attraction.impl.data.AttractionApi
import com.attraction.impl.data.mappers.AttractionMapper
import com.attraction.impl.data.models.dto.LandmarkRequest
import com.attraction.impl.data.models.dto.SendReviewRequest
import com.attraction.impl.data.models.local.Attraction
import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.networkCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AttractionInteractor @Inject constructor(
    private val api: AttractionApi,
    private val attractionMapper: AttractionMapper
) {

    suspend fun getLandmarkInfo(id: String): ResponseState<Attraction> {
        return withContext(Dispatchers.IO) {
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    val response = api.getLandmark(LandmarkRequest(id = id))
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "attraction", time)

                    return@withContext ResponseState.Success(
                        data = attractionMapper.mapResponse(response)
                    )
                },
                catch = { throwable ->
                    Analytics.reportNetworkError(route = "attraction", throwable = throwable)
                    return@withContext ResponseState.Error.Default()
                }
            )
        }
    }

    suspend fun sendReview(id: String, text: String, stars: Int): ResponseState<Unit> {
        return withContext(Dispatchers.IO) {
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    api.addReview(SendReviewRequest(id = id, text = text, stars = stars))
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "send_review", time)

                    return@withContext ResponseState.Success(Unit)
                }, catch = { throwable ->
                    Analytics.reportNetworkError(route = "send_review", throwable = throwable)
                    return@withContext ResponseState.Error.Default()
                }
            )
        }
    }
}