package com.splash.impl.di

import com.splash.impl.ui.SplashViewModel
import dagger.Component

@SplashScope
@Component(
    dependencies = [SplashDependencies::class]
)
interface SplashComponent {

    val viewModel: SplashViewModel

    @Component.Factory
    interface Factory {
        fun create(
            guideDependencies: SplashDependencies
        ): SplashComponent
    }
}
