package com.service.impl.data.models.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ServiceRequest(
    @SerializedName("uid")
    val id: String
)