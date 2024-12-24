package com.item.impl.di

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
import com.item.api.PromoItemEntry
import com.item.impl.PromoItemEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PromoItemNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(PromoItemEntry::class)
    fun promoItemEntry(promoItemEntry: PromoItemEntryImpl): FeatureEntry
}
