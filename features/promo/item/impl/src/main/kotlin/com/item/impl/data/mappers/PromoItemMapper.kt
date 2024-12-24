package com.item.impl.data.mappers

import android.graphics.BitmapFactory
import android.util.Base64
import com.item.impl.data.models.dto.PromoItemDto
import com.item.impl.data.models.local.PromoItem
import javax.inject.Inject

class PromoItemMapper @Inject constructor() {

    fun map(dto: PromoItemDto): PromoItem {
        val decodedImage = Base64.decode(dto.code, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.size)
        return PromoItem(
            name = dto.name,
            imageUrl = dto.imageUrl,
            description = dto.description,
            type = dto.type,
            endDate = dto.endDate,
            warning = dto.warning,
            code = bitmap,
            id = dto.id,
        )
    }
}
