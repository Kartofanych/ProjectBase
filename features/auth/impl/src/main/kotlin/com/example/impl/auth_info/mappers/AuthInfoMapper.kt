package com.example.impl.auth_info.mappers

import com.example.api.models.AuthInfo
import com.example.impl.auth_info.models.AuthInfoDto

fun AuthInfo.toDto() = AuthInfoDto(
    token = token,
)

fun AuthInfoDto.toModel() = AuthInfo(
    token = token,
)
