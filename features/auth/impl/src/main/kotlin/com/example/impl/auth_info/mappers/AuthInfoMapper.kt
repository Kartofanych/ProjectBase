package com.example.impl.auth_info.mappers

import com.example.api.models.AuthInfo
import com.example.impl.auth_info.models.AuthInfoDto

fun AuthInfo.toDto() = AuthInfoDto(
    id = id,
)

fun AuthInfoDto.toModel() = AuthInfo(
    id = id,
)
