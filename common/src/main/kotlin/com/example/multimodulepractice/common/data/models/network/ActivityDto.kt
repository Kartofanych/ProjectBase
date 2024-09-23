package com.example.multimodulepractice.common.data.models.network

import com.google.gson.annotations.SerializedName

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
    enum class ActivityTypeDto {
        @SerializedName("attraction")
        ATTRACTION,

        @SerializedName("service")
        SERVICE
    }
}