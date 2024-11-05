package com.start.impl.di

import com.start.impl.ui.ProcessViewModel
import dagger.Component

@ProcessScope
@Component(
    dependencies = [ProcessDependencies::class],
    modules = []
)
interface ProcessComponent {

    val viewModel: ProcessViewModel

    @Component.Factory
    interface Factory {
        fun create(mainDependencies: ProcessDependencies): ProcessComponent
    }
}
