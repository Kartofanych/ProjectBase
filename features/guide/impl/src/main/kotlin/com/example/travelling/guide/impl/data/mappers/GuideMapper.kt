package com.example.travelling.guide.impl.data.mappers

import com.example.travelling.guide.impl.data.models.GuideResponseDto
import com.example.travelling.guide.impl.data.models.TopicDto
import com.example.travelling.guide.impl.ui.models.GuideResponse
import com.example.travelling.guide.impl.ui.models.Topic
import javax.inject.Inject

class GuideMapper @Inject constructor() {

    fun map(responseDto: GuideResponseDto): GuideResponse {
        return GuideResponse(topics = responseDto.topics.map { it.toTopic() })
    }

    private fun TopicDto.toTopic(): Topic {
        return Topic(
            texts = texts,
            image = image
        )
    }

}