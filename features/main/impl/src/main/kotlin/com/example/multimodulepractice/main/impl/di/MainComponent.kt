package com.example.multimodulepractice.main.impl.di

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.main.impl.repositories.AttractionRepository
import com.example.multimodulepractice.main.impl.repositories.RecommendedAttractionsRepository
import com.example.multimodulepractice.main.impl.ui.list.ListViewModel
import com.example.multimodulepractice.main.impl.ui.map.MapViewModel
import com.example.multimodulepractice.main.impl.ui.profile.ProfileViewModel
import dagger.Component

@AppScope
@Component(
    dependencies = [MainDependencies::class],
    modules = [MainDataModule::class]
)
interface MainComponent {
    val mapViewModel: MapViewModel

    val listViewModel: ListViewModel

    val profileViewModel: ProfileViewModel

    val attractionRepository: AttractionRepository

    val recommendedAttractionsRepository: RecommendedAttractionsRepository

    @Component.Factory
    interface Factory {
        fun create(mainDependencies: MainDependencies): MainComponent
    }
}
