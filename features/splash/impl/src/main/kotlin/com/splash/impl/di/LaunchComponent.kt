package com.splash.impl.di

import com.splash.impl.ui.LaunchViewModel
import dagger.Component

@LaunchScope
@Component(
    dependencies = [LaunchDependencies::class]
)
interface LaunchComponent {

    val viewModel: LaunchViewModel

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: LaunchDependencies
        ): LaunchComponent
    }
}
