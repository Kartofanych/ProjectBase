package com.reviews.impl.data

import com.example.multimodulepractice.common.data.models.local.ResponseState
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
            try {
                val result = api.reviewsPage(ReviewsPageRequestDto(id = id))
                ResponseState.Success(reviewsMapper.mapPage(result))
            } catch (exception: Exception) {
                ResponseState.Error.Default()
            }
        }
    }

    suspend fun loadReviews(
        id: String,
        cursor: String,
        sortId: String = "recent"
    ): ResponseState<ReviewsListResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val result =
                    api.reviews(ReviewsListRequestDto(id = id, cursor = cursor, sortId = sortId))
                ResponseState.Success(reviewsMapper.mapList(result))
            } catch (exception: Exception) {
                ResponseState.Error.Default()
            }
        }
    }
}
