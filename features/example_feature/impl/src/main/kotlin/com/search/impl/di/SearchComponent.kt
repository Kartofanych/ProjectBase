package com.search.impl.di

import com.search.impl.ui.SearchViewModel
import dagger.Component

@SearchScope
@Component(
    dependencies = [SearchDependencies::class],
    modules = []
)
interface SearchComponent {

    val viewModel: SearchViewModel

    @Component.Factory
    interface Factory {
        fun create(mainDependencies: SearchDependencies): SearchComponent
    }
}