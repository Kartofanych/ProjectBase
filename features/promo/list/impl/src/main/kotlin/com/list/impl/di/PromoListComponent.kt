package com.list.impl.di

import com.list.impl.ui.PromoListViewModel
import dagger.Component

@PromoListScope
@Component(
    dependencies = [PromoListDependencies::class],
    modules = [PromoListModule::class]
)
interface PromoListComponent {

    val viewModel: PromoListViewModel

    @Component.Factory
    interface Factory {
        fun create(dependencies: PromoListDependencies): PromoListComponent
    }
}
