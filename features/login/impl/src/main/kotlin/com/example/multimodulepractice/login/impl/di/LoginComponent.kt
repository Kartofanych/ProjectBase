package com.example.multimodulepractice.login.impl.di

import com.example.multimodulepractice.common.navigation.Destinations
import com.example.multimodulepractice.login.impl.ui.LoginViewModel
import dagger.Component

@LoginScope
@Component(
    dependencies = [LoginDependencies::class],
    modules = [NavigationModule::class, LoginApiModule::class]
)
interface LoginComponent {

    val viewModel: LoginViewModel

    val destinations: Destinations

    @Component.Factory
    interface Factory {
        fun create(dependencies: LoginDependencies): LoginComponent
    }
}
