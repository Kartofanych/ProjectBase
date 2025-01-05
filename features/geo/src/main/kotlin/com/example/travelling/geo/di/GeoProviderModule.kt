package com.example.travelling.geo.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.geo.repository.GeoRepository
import com.example.travelling.geo.repository.GeoRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface GeoProviderModule {

    @Binds
    @AppScope
    fun bindGeoRepository(geoRepositoryImpl: GeoRepositoryImpl): GeoRepository

}