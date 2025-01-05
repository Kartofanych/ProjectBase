package com.promo.impl.di

import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
import com.promo.api.PromoEntry
import com.promo.impl.PromoEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PromoNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(PromoEntry::class)
    fun entry(entry: PromoEntryImpl): FeatureEntry
}
