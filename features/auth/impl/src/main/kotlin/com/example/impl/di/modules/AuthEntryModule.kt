package com.example.impl.di.modules

import com.example.api.AuthEntry
import com.example.common.FeatureEntry
import com.example.common.di.AppScope
import com.example.common.di.FeatureEntryKey
import com.example.impl.AuthEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AuthEntryModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(AuthEntry::class)
    fun authFeatureEntry(entry: AuthEntryImpl): FeatureEntry
}
