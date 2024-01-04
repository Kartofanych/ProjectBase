package com.example.impl.di

import dagger.Component

@AuthScope
@Component(
    dependencies = [AuthDependencies::class],
    modules = []
)
interface AuthComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: AuthDependencies): AuthComponent
    }
}