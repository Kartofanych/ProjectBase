package com.reviews.impl.data

import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.networkCall
import com.reviews.api.SendReviewInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendReviewInteractorImpl @Inject constructor(
    private val api: ReviewsApi,
) : SendReviewInteractor {

    override suspend fun sendReview(id: String, text: String, stars: Int): ResponseState<Unit> {
        return withContext(Dispatchers.IO) {
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    api.addReview(
                        com.reviews.impl.data.models.dto.SendReviewRequest(
                            id = id,
                            text = text,
                            stars = stars
                        )
                    )
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