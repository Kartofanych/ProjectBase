package com.inno.impl.data.mappers

import android.annotation.SuppressLint
import com.example.common.models.local.Image
import com.inno.landmark.data.LandmarkAudioGid
import com.inno.landmark.data.LandmarkCategory
import com.inno.landmark.data.LandmarkResponse
import com.inno.impl.data.network.models.response.LandmarkAudioGidDto
import com.inno.impl.data.network.models.response.LandmarkCategoryDto
import com.inno.impl.data.network.models.response.LandmarkResponseDto
import javax.inject.Inject

class LandmarkMapper @Inject constructor(
    private val colorMapper: ColorMapper
) {

    fun mapResponse(response: LandmarkResponseDto): LandmarkResponse {
        return LandmarkResponse(
            name = response.name,
            info = response.info,
            address = response.address,
            categories = response.categories.map { mapCategory(it) },
            images = response.images.map { it.url },
            audioGuides = response.audioGuides.map { mapAudioGid(it) }
        )
    }

    private fun mapCategory(categoryDto: LandmarkCategoryDto): LandmarkCategory {
        return LandmarkCategory(
            categoryDto.name,
            colorMapper.mapColor(categoryDto.color)
        )
    }

    private fun mapAudioGid(audioGid: LandmarkAudioGidDto): LandmarkAudioGid {
        return LandmarkAudioGid(
            audioGid.id,
            audioGid.title,
            secondsToReadableString(audioGid.time),
            Image(audioGid.icon ?: "")
        )
    }

    @SuppressLint("DefaultLocale")
    private fun secondsToReadableString(seconds: Long): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds % 60)
    }

}