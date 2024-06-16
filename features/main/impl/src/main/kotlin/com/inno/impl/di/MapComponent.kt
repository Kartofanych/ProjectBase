package com.inno.impl.di

import dagger.Component

@MainScope
@Component(
    dependencies = [MapDependencies::class],
    modules = []
)
interface MapComponent {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: MapDependencies
        ): MapComponent
    }
}