package com.filters.api.data.models

class Filters(
    val categories: List<FiltersCategory> = emptyList(),
    val distance: FilterDistance = FilterDistance.EVERYWHERE
)