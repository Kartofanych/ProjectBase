package com.example.multimodulepractice.di.modules

import com.example.multimodulepractice.auth.impl.di.AuthProviderModule
import com.example.multimodulepractice.common.di.MainScope
import com.example.multimodulepractice.geo.di.GeoProviderModule
import com.filters.api.data.FiltersRepository
import com.filters.impl.data.FiltersRepositoryImpl
import com.travelling.impl.di.AppConfigModule
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module(
    includes = [
        AppConfigModule::class,
        AuthProviderModule::class,
        GeoProviderModule::class,
    ]
)
interface DataModule {

    @Binds
    @MainScope
    @Reusable
    fun bindFiltersRepository(filtersRepositoryImpl: FiltersRepositoryImpl): FiltersRepository
}