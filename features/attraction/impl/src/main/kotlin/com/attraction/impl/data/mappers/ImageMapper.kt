package com.attraction.impl.data.mappers

import com.attraction.impl.data.models.dto.ImageDto
import com.example.travelling.common.data.models.local.Image
import javax.inject.Inject

class ImageMapper @Inject constructor() {

    fun map(imageDto: ImageDto): Image {
        return Image(imageDto.url)
    }
}