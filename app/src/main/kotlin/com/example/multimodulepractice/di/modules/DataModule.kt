package com.example.multimodulepractice.di.modules

import com.example.multimodulepractice.auth.impl.di.AuthProviderModule
import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.geo.di.GeoProviderModule
import com.example.multimodulepractice.main.impl.di.MainDataModule
import com.filters.api.data.FiltersRepository
import com.filters.impl.data.FiltersRepositoryImpl
import com.splash.api.domain.CitiesRepository
import com.splash.impl.data.repository.CitiesRepositoryImpl
import com.travelling.impl.di.AppConfigModule
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module(
    includes = [
        AppConfigModule::class,
        AuthProviderModule::class,
        GeoProviderModule::class,
        MainDataModule::class
    ]
)
interface DataModule {

    @Binds
    @AppScope
    @Reusable
    fun bindFiltersRepository(filtersRepositoryImpl: FiltersRepositoryImpl): FiltersRepository

    @Binds
    @AppScope
    @Reusable
    fun bindCitiesRepository(citiesRepositoryImpl: CitiesRepositoryImpl): CitiesRepository
}