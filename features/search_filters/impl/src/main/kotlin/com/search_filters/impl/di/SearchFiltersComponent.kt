package com.search_filters.impl.di

import com.search_filters.impl.ui.SearchFiltersViewModel
import dagger.Component

@SearchFiltersScope
@Component(
    dependencies = [SearchFiltersDependencies::class]
)
interface SearchFiltersComponent {

    val viewModel: SearchFiltersViewModel

    @Component.Factory
    interface Factory {
        fun create(dependencies: SearchFiltersDependencies): SearchFiltersComponent
    }
}