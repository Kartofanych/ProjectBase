package com.service.impl.di

import com.service.impl.ui.ServiceViewModel
import dagger.BindsInstance
import dagger.Component

@ServiceScope
@Component(dependencies = [ServiceDependencies::class])
interface ServiceComponent {

    val viewModel: ServiceViewModel

    @Component.Factory
    interface Factory {
        fun create(
            serviceDependencies: ServiceDependencies,
            @BindsInstance id: String
        ): ServiceComponent
    }
}