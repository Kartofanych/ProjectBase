package com.example.multimodulepractice.auth.impl.auth_info.mappers

import com.example.multimodulepractice.auth.impl.auth_info.models.AuthInfoDto
import com.example.multimodulepractice.auth.models.AuthInfo

fun AuthInfo.toDto(): AuthInfoDto {
    return when (this) {
        AuthInfo.Guest -> AuthInfoDto.Guest
        is AuthInfo.User -> AuthInfoDto.User(token, name, mail, image)
    }
}

fun AuthInfoDto.toModel(): AuthInfo {
    return when (this) {
        AuthInfoDto.Guest -> AuthInfo.Guest
        is AuthInfoDto.User -> AuthInfo.User(token, name, gmail, image)
    }
}