package com.splash.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class LaunchResponseDto(
    @SerializedName("cities")
    val cities: List<CityDto>,

    @SerializedName("filters")
    val filters: FiltersDto
)
