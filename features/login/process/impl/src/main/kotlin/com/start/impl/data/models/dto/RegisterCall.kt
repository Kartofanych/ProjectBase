package com.start.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class RegisterCall(
    @SerializedName("email")
    val email: String
)
