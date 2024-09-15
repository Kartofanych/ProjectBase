package com.attraction.impl.data.mappers

import com.example.multimodulepractice.common.data.models.local.Image
import com.attraction.impl.data.models.dto.ImageDto
import javax.inject.Inject

class ImageMapper @Inject constructor() {

    fun map(imageDto: ImageDto): Image {
        return Image(imageDto.url)
    }
}