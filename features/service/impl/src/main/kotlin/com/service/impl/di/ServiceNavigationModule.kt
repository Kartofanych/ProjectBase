package com.service.impl.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.service.api.ServiceEntry
import com.service.impl.ServiceEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ServiceNavigationModule {
    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(ServiceEntry::class)
    fun serviceNavigation(serviceEntry: ServiceEntryImpl): FeatureEntry
}