package com.item.impl.di

import com.item.impl.ui.PromoItemViewModel
import dagger.BindsInstance
import dagger.Component

@PromoItemScope
@Component(
    dependencies = [PromoItemDependencies::class],
    modules = [PromoItemModule::class]
)
interface PromoItemComponent {

    val viewModel: PromoItemViewModel

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance promoId: String,
            dependencies: PromoItemDependencies,
        ): PromoItemComponent
    }
}
