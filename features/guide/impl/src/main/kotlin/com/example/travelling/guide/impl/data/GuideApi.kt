package com.example.travelling.guide.impl.data

import com.example.travelling.guide.impl.data.models.GuideRequestDto
import com.example.travelling.guide.impl.data.models.GuideResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface GuideApi {

    @POST("attraction_guide")
    suspend fun attractionGuide(@Body guideRequestDto: GuideRequestDto): GuideResponseDto

}