package com.example.travelling.guide.impl.di

import com.example.travelling.guide.impl.ui.GuideViewModel
import dagger.BindsInstance
import dagger.Component

@GuideScope
@Component(
    dependencies = [GuideDependencies::class]
)
interface GuideComponent {

    val viewModel: GuideViewModel

    @Component.Factory
    interface Factory {
        fun create(
            guideDependencies: GuideDependencies,
            @BindsInstance id: String
        ): GuideComponent
    }
}