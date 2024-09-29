package com.example.multimodulepractice.main.impl.di

import com.example.multimodulepractice.common.navigation.Destinations
import com.example.multimodulepractice.main.impl.data.repositories.RecommendedAttractionsRepositoryImpl
import com.example.multimodulepractice.main.impl.ui.MainViewModel
import com.main.common.di.MainScope
import dagger.Component

@MainScope
@Component(
    dependencies = [MainDependencies::class],
    modules = [MainLocalDataModule::class, MainLocalNavigationModule::class]
)
interface MainComponent {

    val recommendedAttractionsRepository: RecommendedAttractionsRepositoryImpl

    val destinations: Destinations

    val viewModel: MainViewModel

    @Component.Factory
    interface Factory {
        fun create(mainDependencies: MainDependencies): MainComponent
    }
}
