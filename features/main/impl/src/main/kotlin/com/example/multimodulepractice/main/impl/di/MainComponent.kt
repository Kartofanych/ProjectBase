package com.example.multimodulepractice.main.impl.di

import com.example.multimodulepractice.common.di.MainScope
import com.example.multimodulepractice.common.navigation.Destinations
import com.example.multimodulepractice.main.impl.data.repositories.RecommendedAttractionsRepositoryImpl
import com.favourites.impl.ui.FavouritesViewModel
import com.search.impl.di.ListDependencies
import com.search.impl.ui.ListViewModel
import dagger.Component

@MainScope
@Component(
    dependencies = [MainDependencies::class],
    modules = [MainLocalDataModule::class, MainLocalNavigationModule::class]
)
interface MainComponent {

    val listViewModel: ListViewModel

    val favouritesViewModel: FavouritesViewModel

    val recommendedAttractionsRepository: RecommendedAttractionsRepositoryImpl

    val destinations: Destinations

    val listDependencies: ListDependencies

    @Component.Factory
    interface Factory {
        fun create(mainDependencies: MainDependencies): MainComponent
    }
}
