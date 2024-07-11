package com.example.multimodulepractice.guide.impl.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class TopicDto(
    @SerializedName("text")
    val texts: List<String>,

    @SerializedName("image")
    val image: String

)