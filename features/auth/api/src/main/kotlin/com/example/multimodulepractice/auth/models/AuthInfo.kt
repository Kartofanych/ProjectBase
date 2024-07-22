package com.example.multimodulepractice.auth.models

sealed class AuthInfo(
    val _token: String?,
) {
    object Guest : AuthInfo(null)

    data class User(
        val token: String,
        val name: String,
        val gmail: String,
        val image: String
    ) : AuthInfo(token)
}
