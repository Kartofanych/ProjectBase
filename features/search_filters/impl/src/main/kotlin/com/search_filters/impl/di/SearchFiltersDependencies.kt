package com.search_filters.impl.di

import com.search_filters.api.data.domain.SearchFilterRepository
import javax.inject.Inject

class SearchFiltersDependencies @Inject constructor(
    val searchFilterRepository: SearchFilterRepository
)