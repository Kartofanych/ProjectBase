package com.example.multimodulepractice.guide.impl.di

import com.example.multimodulepractice.guide.impl.ui.GuideViewModel
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