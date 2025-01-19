package com.reviews.impl.data

import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.networkCall
import com.reviews.impl.data.models.dto.ReviewsListRequestDto
import com.reviews.impl.data.models.dto.ReviewsPageRequestDto
import com.reviews.impl.data.models.local.ReviewsListResponse
import com.reviews.impl.data.models.local.ReviewsPageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReviewsInteractor @Inject constructor(
    private val api: ReviewsApi,
    private val reviewsMapper: ReviewsMapper,
) {

    suspend fun loadReviewsPage(id: String): ResponseState<ReviewsPageResponse> {
        return withContext(Dispatchers.IO) {
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    val result = api.reviewsPage(ReviewsPageRequestDto(id = id))
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "reviews_page", millis = time)
                    ResponseState.Success(reviewsMapper.mapPage(result))
                },
                catch = { throwable ->
                    Analytics.reportNetworkError(route = "reviews_page", throwable = throwable)
                    ResponseState.Error.Default()
                }
            )
        }
    }

    suspend fun loadReviews(
        id: String,
        cursor: String,
        sortId: String? = null
    ): ResponseState<ReviewsListResponse> {
        return withContext(Dispatchers.IO) {
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    val result =
                        api.reviews(
                            ReviewsListRequestDto(id = id, cursor = cursor, sortId = sortId)
                        )
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "reviews", millis = time)
                    ResponseState.Success(reviewsMapper.mapList(result))
                }, catch = { throwable ->
                    Analytics.reportNetworkError(route = "reviews", throwable = throwable)
                    ResponseState.Error.Default()
                }
            )
        }
    }
}
