package com.filters.api.data.models

class Filters(
    val categories: List<FiltersCategory>,
    val distance: FilterDistance = FilterDistance.EVERYWHERE
)