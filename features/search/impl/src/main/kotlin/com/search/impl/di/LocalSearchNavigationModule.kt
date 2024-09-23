package com.search.impl.di

import com.search_filters.impl.di.SearchFiltersNavigationModule
import com.search_filters.impl.di.SearchScreenNavigationModule
import dagger.Module

@Module(
    includes = [
        SearchFiltersNavigationModule::class,
        SearchScreenNavigationModule::class
    ]
)
interface LocalSearchNavigationModule