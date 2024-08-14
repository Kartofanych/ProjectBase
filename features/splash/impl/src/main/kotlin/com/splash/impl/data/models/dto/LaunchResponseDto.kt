package com.splash.impl.data.models.dto

import com.google.gson.annotations.SerializedName

class LaunchResponseDto(
    @SerializedName("cities")
    val cities: List<CityDto>,

    @SerializedName("filters")
    val filters: FiltersDto
)
