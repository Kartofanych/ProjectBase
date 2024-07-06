package com.inno.impl.data

import com.inno.impl.data.models.GuideRequestDto
import com.inno.impl.data.models.GuideResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface GuideApi {

    @POST("attraction_guide")
    suspend fun attractionGuide(@Body guideRequestDto: GuideRequestDto): GuideResponseDto

}