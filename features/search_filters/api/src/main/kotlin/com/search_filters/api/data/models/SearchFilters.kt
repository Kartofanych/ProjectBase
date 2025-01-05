package com.search_filters.api.data.models

import com.example.travelling.common.data.models.local.City
import com.example.travelling.common.data.models.local.FilterDistance

data class SearchFilters(
    val distance: FilterDistance,
    val ratingFrom: Float,
    val cities: List<SearchFiltersCity>
) {
    companion object {
        private const val DEFAULT_RATING_FROM = 3f

        fun empty(cities: List<City>): SearchFilters {
            return SearchFilters(
                distance = FilterDistance.EVERYWHERE,
                ratingFrom = DEFAULT_RATING_FROM,
                cities = cities.mapIndexed { index, city ->
                    SearchFiltersCity(
                        city.id,
                        city.name,
                        true,
                        index
                    )
                }
            )
        }
    }
}