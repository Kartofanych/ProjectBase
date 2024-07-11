package com.example.multimodulepractice.main.impl.data.mappers

import android.annotation.SuppressLint
import com.example.multimodulepractice.common.models.local.Image
import com.example.multimodulepractice.landmark.data.LandmarkAudioGid
import com.example.multimodulepractice.landmark.data.AttractionCategory
import com.example.multimodulepractice.landmark.data.LandmarkResponse
import com.example.multimodulepractice.main.impl.data.network.models.response.LandmarkAudioGidDto
import com.example.multimodulepractice.main.impl.data.network.models.response.LandmarkCategoryDto
import com.example.multimodulepractice.main.impl.data.network.models.response.LandmarkResponseDto
import javax.inject.Inject

class LandmarkMapper @Inject constructor(
    private val colorMapper: ColorMapper
) {

    fun mapResponse(response: LandmarkResponseDto): LandmarkResponse {
        return LandmarkResponse(
            id = response.id,
            name = response.name,
            info = response.info,
            address = response.address,
            categories = response.categories.map { mapCategory(it) },
            images = response.images.map { it.url },
            audioGuides = response.audioGuides.map { mapAudioGid(it) }
        )
    }

    fun mapCategory(categoryDto: LandmarkCategoryDto): AttractionCategory {
        return AttractionCategory(
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