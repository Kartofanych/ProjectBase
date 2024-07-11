package com.example.multimodulepractice.auth.impl.auth_info.models

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class AuthInfoDto(
    val token: String?,
) {
    companion object {
        val EMPTY = AuthInfoDto(null)
    }
}
