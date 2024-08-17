package com.splash.impl.data

import com.splash.impl.data.models.dto.LaunchRequest
import com.splash.impl.data.models.dto.LaunchResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LaunchApi {

    @POST("launch")
    suspend fun launch(@Body launchRequest: LaunchRequest): LaunchResponseDto
}