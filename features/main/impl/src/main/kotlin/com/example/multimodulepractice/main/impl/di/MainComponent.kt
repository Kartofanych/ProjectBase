package com.example.multimodulepractice.main.impl.di

import com.example.multimodulepractice.common.di.MainScope
import com.example.multimodulepractice.main.impl.repositories.RecommendedAttractionsRepository
import com.example.multimodulepractice.main.impl.ui.list.ListViewModel
import com.example.multimodulepractice.main.impl.ui.profile.ProfileViewModel
import dagger.Component

@MainScope
@Component(
    dependencies = [MainDependencies::class],
    modules = [MainDataModule::class]
)
interface MainComponent {

    val listViewModel: ListViewModel

    val profileViewModel: ProfileViewModel

    val recommendedAttractionsRepository: RecommendedAttractionsRepository

    @Component.Factory
    interface Factory {
        fun create(mainDependencies: MainDependencies): MainComponent
    }
}
