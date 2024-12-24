package com.list.impl.data.mappers

import com.list.impl.data.models.dto.PromoCodeDto
import com.list.impl.data.models.dto.PromoCodesResponseDto
import com.list.impl.data.models.local.PromoCode
import com.list.impl.data.models.local.PromoCodesResponse
import javax.inject.Inject

class PromoListMapper @Inject constructor() {

    fun map(dto: PromoCodesResponseDto): PromoCodesResponse {
        return PromoCodesResponse(
            list = dto.list.map(::mapPromo)
        )
    }

    private fun mapPromo(dto: PromoCodeDto): PromoCode {
        return PromoCode(
            title = dto.name,
            endDate = dto.endDate,
            imageUrl = dto.imageUrl,
            subtitle = dto.description,
            token = dto.token
        )
    }
}
