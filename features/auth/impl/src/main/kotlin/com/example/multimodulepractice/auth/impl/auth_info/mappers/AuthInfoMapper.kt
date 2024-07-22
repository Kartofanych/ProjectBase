package com.example.multimodulepractice.auth.impl.auth_info.mappers

import com.example.multimodulepractice.auth.models.AuthInfo
import com.example.multimodulepractice.auth.impl.auth_info.models.AuthInfoDto

fun AuthInfo.toDto(): AuthInfoDto {
    return when (this) {
        AuthInfo.Guest -> AuthInfoDto.Guest
        is AuthInfo.User -> AuthInfoDto.User(token, name, gmail, image)
    }
}

fun AuthInfoDto.toModel(): AuthInfo {
    return when (this) {
        AuthInfoDto.Guest -> AuthInfo.Guest
        is AuthInfoDto.User -> AuthInfo.User(token, name, gmail, image)
    }
}