package com.promo.impl.di

import com.example.multimodulepractice.common.navigation.Destinations
import com.promo.impl.ui.PromoViewModel
import dagger.Component

@PromoScope
@Component(
    dependencies = [PromoDependencies::class],
    modules = [NavigationModule::class]
)
interface PromoComponent {

    val viewModel: PromoViewModel

    val destinations: Destinations

    @Component.Factory
    interface Factory {
        fun create(dependencies: PromoDependencies): PromoComponent
    }
}