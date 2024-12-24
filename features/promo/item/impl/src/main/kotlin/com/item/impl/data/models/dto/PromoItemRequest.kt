package com.item.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class PromoItemRequest(
    @SerializedName("token")
    val token: String
)
