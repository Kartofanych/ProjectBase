package com.start.impl.data.mappers

import com.start.impl.data.models.dto.ValidationResponseDto
import com.start.impl.data.models.local.ValidationResponse
import javax.inject.Inject

class ValidateMapper @Inject constructor() {

    fun map(dto: ValidationResponseDto): ValidationResponse {
        return ValidationResponse(
            token = dto.token,
            name = dto.name,
            icon = dto.icon
        )
    }
}
