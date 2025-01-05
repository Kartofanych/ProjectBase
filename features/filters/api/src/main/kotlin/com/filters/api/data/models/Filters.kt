package com.filters.api.data.models

import com.example.travelling.common.data.models.local.FilterDistance

data class Filters(
    val categories: List<FiltersCategory> = emptyList(),
    val distance: FilterDistance = FilterDistance.EVERYWHERE
)