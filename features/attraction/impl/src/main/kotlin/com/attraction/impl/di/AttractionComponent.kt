package com.attraction.impl.di

import com.attraction.impl.ui.AttractionViewModel
import dagger.BindsInstance
import dagger.Component

@AttractionScope
@Component(
    dependencies = [AttractionDependencies::class],
    modules = []
)
interface AttractionComponent {

    val viewModel: AttractionViewModel

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: AttractionDependencies,
            @BindsInstance id: String
        ): AttractionComponent
    }
}