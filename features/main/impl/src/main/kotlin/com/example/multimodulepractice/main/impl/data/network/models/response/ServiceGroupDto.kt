package com.example.multimodulepractice.main.impl.data.network.models.response

import com.google.gson.annotations.SerializedName

data class ServiceGroupDto(

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("services")
    val services: List<ServiceDto>
)
