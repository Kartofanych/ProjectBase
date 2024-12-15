package com.attraction.impl.data

import com.attraction.impl.data.models.dto.LandmarkRequest
import com.attraction.impl.data.models.dto.LandmarkResponseDto
import com.attraction.impl.data.models.dto.SendReviewRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AttractionApi {

    @POST("attraction")
    suspend fun getLandmark(@Body body: LandmarkRequest): LandmarkResponseDto


    @POST("add_review")
    suspend fun addReview(@Body body: SendReviewRequest)
}