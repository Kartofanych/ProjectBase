package com.filters.api.data.models

data class FiltersCategory(
    val id: String,
    val index: Int,
    val name: String,
    val count: Int,
    val isSelected: Boolean
)