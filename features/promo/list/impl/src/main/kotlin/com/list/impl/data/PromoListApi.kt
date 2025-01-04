package com.list.impl.data

import com.list.impl.data.models.dto.PromoCodesResponseDto
import retrofit2.http.POST

interface PromoListApi {

    @POST("promocodes")
    suspend fun promoCodes(): PromoCodesResponseDto
}
