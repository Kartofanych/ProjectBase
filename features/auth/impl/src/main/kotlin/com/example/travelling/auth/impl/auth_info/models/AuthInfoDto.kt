package com.example.travelling.auth.impl.auth_info.models

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
sealed class AuthInfoDto {
    @Keep
    @Serializable
    object Guest : AuthInfoDto()

    @Keep
    @Serializable
    data class User(
        val token: String,
        val name: String,
        val gmail: String,
        val image: String
    ) : AuthInfoDto()
}

