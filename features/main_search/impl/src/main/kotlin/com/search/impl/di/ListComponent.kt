package com.search.impl.di

import dagger.Component

@ListScope
@Component(
    dependencies = [ListDependencies::class]
)
interface ListComponent {

    @Component.Factory
    interface Factory {
        fun create(listDependencies: ListDependencies): ListComponent
    }
}