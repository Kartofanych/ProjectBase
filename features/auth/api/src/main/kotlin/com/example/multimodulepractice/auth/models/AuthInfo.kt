package com.example.multimodulepractice.auth.models

sealed interface AuthInfo {
    object Guest : AuthInfo

    data class User(
        val token: String,
        val name: String,
        val gmail: String,
        val image: String
    ) : AuthInfo
}
