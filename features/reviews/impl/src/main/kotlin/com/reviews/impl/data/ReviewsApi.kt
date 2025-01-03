package com.reviews.impl.data

import com.reviews.impl.data.models.dto.ReviewsListRequestDto
import com.reviews.impl.data.models.dto.ReviewsListResponseDto
import com.reviews.impl.data.models.dto.ReviewsPageRequestDto
import com.reviews.impl.data.models.dto.ReviewsPageResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ReviewsApi {

    @POST("review_page")
    suspend fun reviewsPage(@Body body: ReviewsPageRequestDto): ReviewsPageResponseDto

    @POST("reviews")
    suspend fun reviews(@Body body: ReviewsListRequestDto): ReviewsListResponseDto
}
