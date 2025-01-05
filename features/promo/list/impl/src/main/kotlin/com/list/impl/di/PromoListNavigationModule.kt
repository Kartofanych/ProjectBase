package com.list.impl.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.list.api.PromoListEntry
import com.list.impl.PromoListEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PromoListNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(PromoListEntry::class)
    fun promoListEntry(entry: PromoListEntryImpl): FeatureEntry
}
