package com.example.travelling.common.data.models.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
enum class ObjectType {

    @SerializedName("attraction")
    ATTRACTION,

    @SerializedName("service")
    SERVICE,
}