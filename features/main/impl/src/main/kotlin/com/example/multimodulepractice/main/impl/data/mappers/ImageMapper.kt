package com.example.multimodulepractice.main.impl.data.mappers

import com.example.multimodulepractice.common.models.local.Image
import com.main.common.data.dto.ImageDto
import javax.inject.Inject

class ImageMapper @Inject constructor() {

    fun map(imageDto: ImageDto): Image {
        return Image(imageDto.url)
    }
}