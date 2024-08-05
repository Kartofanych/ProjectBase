package com.filters.impl.data

import com.filters.api.data.models.Filters
import com.filters.api.data.FiltersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class FiltersRepositoryImpl @Inject constructor() : FiltersRepository {

    private var defaultFilters: Filters? = null

    private val _filters = MutableStateFlow<Filters?>(null)
    override val filters = _filters.asStateFlow()

    override fun setDefaultFilters(filters: Filters) {
        defaultFilters = filters
        _filters.value = filters
    }

    override fun updateFilters(filters: Filters) {
        _filters.value = filters
    }
}