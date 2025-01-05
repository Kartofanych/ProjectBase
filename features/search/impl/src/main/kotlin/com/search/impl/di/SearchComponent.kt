package com.search.impl.di

import com.example.travelling.common.navigation.Destinations
import com.search_filters.impl.di.SearchFiltersDependencies
import dagger.Component

@SearchScope
@Component(
    dependencies = [SearchDependencies::class],
    modules = [
        SearchFiltersDataModule::class,
        SearchApiModule::class,
        LocalSearchNavigationModule::class
    ]
)
interface SearchComponent {

    val destinations: Destinations

    val searchScreenDependencies: SearchFiltersDependencies

    val searchFiltersDependencies: SearchFiltersDependencies

    @Component.Factory
    interface Factory {
        fun create(dependencies: SearchDependencies): SearchComponent
    }
}