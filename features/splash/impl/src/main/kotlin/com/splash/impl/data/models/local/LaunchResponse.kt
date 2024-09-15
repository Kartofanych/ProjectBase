package com.splash.impl.data.models.local

import com.example.multimodulepractice.common.data.models.local.City
import com.filters.api.data.models.Filters

class LaunchResponse(
    val cities: List<City>,
    val filters: Filters
)
