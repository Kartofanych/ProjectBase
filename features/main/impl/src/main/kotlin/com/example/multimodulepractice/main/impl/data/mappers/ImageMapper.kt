package com.example.multimodulepractice.main.impl.data.mappers

import com.example.multimodulepractice.common.models.local.Image
import com.example.multimodulepractice.main.impl.data.network.models.response.ImageDto
import javax.inject.Inject

class ImageMapper @Inject constructor() {

    fun map(imageDto: ImageDto): Image {
        return Image(imageDto.url)
    }

}