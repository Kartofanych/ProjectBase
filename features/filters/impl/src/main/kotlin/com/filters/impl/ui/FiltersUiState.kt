package com.filters.impl.ui

import com.filters.api.data.models.FiltersCategory

data class FiltersUiState(
    val categories: List<FiltersCategory>
) {
    companion object {
        val EMPTY = FiltersUiState(emptyList())
    }
}