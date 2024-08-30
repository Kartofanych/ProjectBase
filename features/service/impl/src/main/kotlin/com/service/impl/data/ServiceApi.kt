package com.service.impl.data

import com.service.impl.data.models.network.ServiceRequest
import com.service.impl.data.models.network.ServiceResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceApi {

    @POST("service")
    suspend fun service(@Body request: ServiceRequest): ServiceResponseDto
}