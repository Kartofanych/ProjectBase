package com.inno.impl.data.network

import com.inno.impl.data.network.models.request.LandmarkRequest
import com.inno.impl.data.network.models.request.StartInfoRequest
import com.inno.impl.data.network.models.response.LandmarkResponseDto
import com.inno.impl.data.network.models.response.MapInfoResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {

    @POST("map_info")
    suspend fun getStartInfo(@Body body: StartInfoRequest): MapInfoResponseDto

    @POST("attraction")
    suspend fun getLandmark(@Body body: LandmarkRequest): LandmarkResponseDto

}