package com.example.multimodulepractice.auth.impl.auth_info.mappers

import com.example.multimodulepractice.auth.models.AuthInfo
import com.example.multimodulepractice.auth.impl.auth_info.models.AuthInfoDto

fun AuthInfo.toDto() = AuthInfoDto(
    token = token,
)

fun AuthInfoDto.toModel() = AuthInfo(
    token = token,
)
