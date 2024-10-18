package com.main.common.data

import com.main.common.data.dto.MapInfoRequest
import com.main.common.data.dto.MapInfoResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {

    @POST("map_info")
    suspend fun getStartInfo(@Body body: MapInfoRequest): MapInfoResponseDto
}