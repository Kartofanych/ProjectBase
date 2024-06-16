package com.inno.geo.di

import com.inno.geo.repository.GeoRepository
import com.inno.geo.repository.GeoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface GeoProviderModule {

    @Binds
    @Singleton
    fun bindGeoRepository(geoRepositoryImpl: GeoRepositoryImpl): GeoRepository

}