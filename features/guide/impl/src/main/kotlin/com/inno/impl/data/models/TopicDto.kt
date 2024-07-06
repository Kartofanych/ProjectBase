package com.inno.impl.data.models

import com.google.gson.annotations.SerializedName

data class TopicDto(
    @SerializedName("text")
    val text: String,

    @SerializedName("image")
    val image: String

)