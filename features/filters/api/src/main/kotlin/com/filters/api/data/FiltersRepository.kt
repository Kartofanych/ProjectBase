package com.filters.api.data

import com.filters.api.data.models.Filters
import kotlinx.coroutines.flow.StateFlow

interface FiltersRepository {

    val zeroFilters: Filters

    val filters: StateFlow<Filters?>

    fun setDefaultFilters(filters: Filters)

    fun updateFilters(filters: Filters)
}