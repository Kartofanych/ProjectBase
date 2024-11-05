package com.start.impl.di

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
import com.start.api.ProcessEntry
import com.start.impl.ProcessEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProcessNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(ProcessEntry::class)
    fun processEntry(entry: ProcessEntryImpl): FeatureEntry
}
