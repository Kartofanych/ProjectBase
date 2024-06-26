package com.inno.impl.data.mappers

import com.example.common.models.local.Image
import com.inno.impl.data.network.models.response.ImageDto
import javax.inject.Inject

class ImageMapper @Inject constructor() {

    fun map(imageDto: ImageDto): Image {
        return Image(imageDto.url)
    }

}