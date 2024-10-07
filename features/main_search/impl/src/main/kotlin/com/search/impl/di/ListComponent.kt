package com.search.impl.di

import com.search.impl.ui.ListViewModel
import dagger.Component

@ListScope
@Component(
    dependencies = [ListDependencies::class]
)
internal interface ListComponent {

    val viewModule: ListViewModel

    @Component.Factory
    interface Factory {
        fun create(listDependencies: ListDependencies): ListComponent
    }
}