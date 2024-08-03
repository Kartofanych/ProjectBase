package com.filters.impl.di

import com.filters.impl.ui.FiltersViewModel
import dagger.Component

@Component(
    dependencies = [FiltersDependencies::class]
)
interface FiltersComponent {

    val viewModel: FiltersViewModel

    @Component.Factory
    interface Factory {
        fun create(
            guideDependencies: FiltersDependencies
        ): FiltersComponent
    }
}
