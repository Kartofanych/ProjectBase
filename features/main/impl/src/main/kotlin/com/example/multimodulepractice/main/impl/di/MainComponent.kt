package com.example.multimodulepractice.main.impl.di

import com.main.common.di.MainScope
import com.example.multimodulepractice.common.navigation.Destinations
import com.example.multimodulepractice.main.impl.data.repositories.RecommendedAttractionsRepositoryImpl
import com.search.impl.di.ListDependencies
import dagger.Component

@MainScope
@Component(
    dependencies = [MainDependencies::class],
    modules = [MainLocalDataModule::class, MainLocalNavigationModule::class]
)
interface MainComponent {

    val recommendedAttractionsRepository: RecommendedAttractionsRepositoryImpl

    val destinations: Destinations

    val listDependencies: ListDependencies

    @Component.Factory
    interface Factory {
        fun create(mainDependencies: MainDependencies): MainComponent
    }
}
