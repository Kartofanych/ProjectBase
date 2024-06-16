package com.inno.impl.di

import com.inno.impl.ui.MainFragment
import dagger.Component

@MainScope
@Component(
    dependencies = [MainDependencies::class],
    modules = []
)
interface MainComponent {

    fun inject(mainFragment: MainFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: MainDependencies): MainComponent
    }
}