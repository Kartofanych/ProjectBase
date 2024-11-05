package com.start.impl.di

import com.start.impl.ui.StartViewModel
import dagger.Component

@StartScope
@Component(
    dependencies = [StartDependencies::class],
    modules = []
)
interface StartComponent {

    val viewModel: StartViewModel

    @Component.Factory
    interface Factory {
        fun create(dependencies: StartDependencies): StartComponent
    }
}
