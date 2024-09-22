package com.search_filters.impl.di

import com.search_filters.impl.ui.SearchViewModel
import dagger.Component

@SearchScreenScope
@Component(
    dependencies = [SearchScreenDependencies::class]
)
interface SearchScreenComponent {

    val viewModel: SearchViewModel

    @Component.Factory
    interface Factory {
        fun create(dependencies: SearchScreenDependencies): SearchScreenComponent
    }
}