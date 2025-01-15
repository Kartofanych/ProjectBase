package com.onboarding.impl.di

import com.onboarding.impl.ui.OnboardingViewModel
import dagger.Component

@OnboardingScope
@Component(
    dependencies = [OnboardingDependencies::class],
    modules = []
)
interface OnboardingComponent {

    val viewModel: OnboardingViewModel

    @Component.Factory
    interface Factory {
        fun create(dependencies: OnboardingDependencies): OnboardingComponent
    }
}