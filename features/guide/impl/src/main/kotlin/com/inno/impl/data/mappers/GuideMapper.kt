package com.inno.impl.data.mappers

import com.inno.impl.data.models.GuideResponseDto
import com.inno.impl.data.models.TopicDto
import com.inno.impl.ui.models.GuideResponse
import com.inno.impl.ui.models.Topic
import javax.inject.Inject

class GuideMapper @Inject constructor() {

    fun map(responseDto: GuideResponseDto): GuideResponse {
        return GuideResponse(topics = responseDto.topics.map { it.toTopic() })
    }

    private fun TopicDto.toTopic(): Topic {
        return Topic(
            text = text,
            image = image
        )
    }

}