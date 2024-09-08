package com.main.common.data.dto

import com.google.gson.annotations.SerializedName

data class ServiceGroupDto(

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("services")
    val services: List<ServiceDto>
)
