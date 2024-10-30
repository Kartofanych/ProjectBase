package com.attraction.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ServiceGroupDto(

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("services")
    val services: List<ServiceDto>
)
