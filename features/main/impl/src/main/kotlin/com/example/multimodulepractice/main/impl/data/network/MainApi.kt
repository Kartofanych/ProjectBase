package com.example.multimodulepractice.main.impl.data.network

import com.example.multimodulepractice.main.impl.data.network.models.request.LandmarkRequest
import com.example.multimodulepractice.main.impl.data.network.models.request.StartInfoRequest
import com.example.multimodulepractice.main.impl.data.network.models.response.LandmarkResponseDto
import com.example.multimodulepractice.main.impl.data.network.models.response.MapInfoResponseDto
import com.example.multimodulepractice.main.impl.data.network.models.response.RecommendationsResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {

    @POST("map_info")
    suspend fun getStartInfo(@Body body: StartInfoRequest): MapInfoResponseDto

    @POST("attraction")
    suspend fun getLandmark(@Body body: LandmarkRequest): LandmarkResponseDto

    @POST("recommendations")
    suspend fun getRecommendations(@Body body: StartInfoRequest): RecommendationsResponseDto

}