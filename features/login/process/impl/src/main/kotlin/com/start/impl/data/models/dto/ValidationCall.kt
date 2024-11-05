package com.start.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ValidationCall(
    @SerializedName("email")
    val email: String,

    @SerializedName("code")
    val code: String,
)
