package com.inno.impl.di

import dagger.Component

@MainScope
@Component(
    dependencies = [MainDependencies::class],
    modules = []
)
interface MainComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: MainDependencies): MainComponent
    }
}