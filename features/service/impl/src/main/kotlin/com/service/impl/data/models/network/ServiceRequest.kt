package com.service.impl.data.models.network

import com.google.gson.annotations.SerializedName

class ServiceRequest(
    @SerializedName("uid")
    val id: String
)