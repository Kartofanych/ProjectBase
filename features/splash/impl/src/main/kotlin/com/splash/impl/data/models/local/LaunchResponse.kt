package com.splash.impl.data.models.local

import com.example.multimodulepractice.common.models.local.City
import com.filters.api.data.models.Filters

sealed interface LaunchResponse {
    data class Success(
        val cities: List<City>,
        val filters: Filters,
    ): LaunchResponse

    data class Error(val err: Exception): LaunchResponse
}
