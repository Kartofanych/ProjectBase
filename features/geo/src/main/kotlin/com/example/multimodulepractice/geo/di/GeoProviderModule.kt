package com.example.multimodulepractice.geo.di

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.geo.repository.GeoRepository
import com.example.multimodulepractice.geo.repository.GeoRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface GeoProviderModule {

    @Binds
    @AppScope
    fun bindGeoRepository(geoRepositoryImpl: GeoRepositoryImpl): GeoRepository

}