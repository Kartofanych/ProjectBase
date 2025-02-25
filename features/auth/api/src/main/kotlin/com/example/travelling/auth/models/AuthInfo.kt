package com.example.travelling.auth.models

sealed interface AuthInfo {
    object Guest : AuthInfo

    data class User(
        val token: String,
        val name: String,
        val mail: String,
        val image: String
    ) : AuthInfo
}
