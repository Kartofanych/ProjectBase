package com.example.impl.auth_info.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoDto(
    val token: String?,
) {
    companion object {
        val EMPTY = AuthInfoDto(null)
    }
}
