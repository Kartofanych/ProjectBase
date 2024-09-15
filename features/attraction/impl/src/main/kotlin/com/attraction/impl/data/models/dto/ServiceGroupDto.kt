package com.attraction.impl.data.models.dto

import com.google.gson.annotations.SerializedName

data class ServiceGroupDto(

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("services")
    val services: List<ServiceDto>
)
