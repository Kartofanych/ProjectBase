package com.example.travelling.common.data.models.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ActivityDto(
    @SerializedName("uid")
    val id: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("tag")
    val tag: String,

    @SerializedName("type")
    val type: ActivityTypeDto,

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("rating")
    val rating: Float
) {
    @Keep
    enum class ActivityTypeDto {
        @SerializedName("attraction")
        ATTRACTION,

        @SerializedName("service")
        SERVICE
    }
}