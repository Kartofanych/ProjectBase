package com.start.impl.data

import com.start.impl.data.models.dto.RegisterCall
import com.start.impl.data.models.dto.ValidationCall
import com.start.impl.data.models.dto.ValidationResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginProcessApi {

    @POST("register")
    suspend fun register(@Body registerBody: RegisterCall)

    @POST("validate_email")
    suspend fun validateEmail(@Body validateBody: ValidationCall): ValidationResponseDto
}
