package com.example.travelling.main.impl.di

import com.example.travelling.common.navigation.Destinations
import com.example.travelling.main.impl.ui.MainViewModel
import com.favourites.impl.ui.FavouritesViewModel
import com.main.common.di.MainScope
import com.search.impl.ui.ListViewModel
import dagger.Component

@MainScope
@Component(
    dependencies = [MainDependencies::class],
    modules = [MainLocalDataModule::class, MainLocalNavigationModule::class, MainBindsModule::class]
)
interface MainComponent {

    val destinations: Destinations

    val viewModel: MainViewModel

    val listViewModel: ListViewModel

    val favouritesViewModel: FavouritesViewModel

    @Component.Factory
    interface Factory {
        fun create(mainDependencies: MainDependencies): MainComponent
    }
}
