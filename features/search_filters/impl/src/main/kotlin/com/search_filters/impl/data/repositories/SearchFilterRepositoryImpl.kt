package com.search_filters.impl.data.repositories

import com.search_filters.api.data.models.SearchFilters
import com.search_filters.api.data.domain.SearchFilterRepository
import com.splash.api.domain.CitiesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SearchFilterRepositoryImpl @Inject constructor(
    citiesRepository: CitiesRepository
) : SearchFilterRepository {

    override val zeroFilters: SearchFilters = SearchFilters.empty(citiesRepository.cities())

    private val _filters = MutableStateFlow(zeroFilters)
    override val filters = _filters.asStateFlow()

    override fun updateFilters(filters: SearchFilters) {
        _filters.update { filters }
    }
}