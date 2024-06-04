package com.inno.impl.di.modules

import com.example.common.FeatureEntry
import com.example.common.di.AppScope
import com.example.common.di.FeatureEntryKey
import com.inno.api.MainEntry
import com.inno.impl.MainEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainEntryModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(MainEntry::class)
    fun authFeatureEntry(entry: MainEntryImpl): FeatureEntry
}
