package com.item.impl.data

import com.item.impl.data.models.dto.PromoItemDto
import com.item.impl.data.models.dto.PromoItemRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface PromoItemApi {

    @POST("/promocode_info")
    suspend fun promoCodeInfo(@Body body: PromoItemRequest): PromoItemDto
}
