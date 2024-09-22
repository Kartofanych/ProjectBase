package com.search_filters.api.data.domain

import com.search_filters.api.data.models.SearchFilters
import kotlinx.coroutines.flow.StateFlow

interface SearchFilterRepository {

    val zeroFilters: SearchFilters

    val filters: StateFlow<SearchFilters>

    fun updateFilters(filters: SearchFilters)
}