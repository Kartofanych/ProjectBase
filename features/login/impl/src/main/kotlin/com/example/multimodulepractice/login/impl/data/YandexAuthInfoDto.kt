package com.example.multimodulepractice.login.impl.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class YandexAuthInfoDto(
    @SerializedName("default_email")
    val email: String,

    @SerializedName("real_name")
    val name: String,

    @SerializedName("default_avatar_id")
    val avatarId: String
)