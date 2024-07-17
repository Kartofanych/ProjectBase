package com.example.multimodulepractice.audio_guide.impl.di

import com.example.multimodulepractice.AudioGuideFeatureEntry
import com.example.multimodulepractice.audio_guide.impl.AudioGuideFeatureImpl
import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AudioGuideNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(AudioGuideFeatureEntry::class)
    fun audioGuideFeature(loginFeatureImpl: AudioGuideFeatureImpl): FeatureEntry

}