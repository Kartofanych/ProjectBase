package com.example.travelling.audio_guide.impl.di

import com.example.travelling.AudioGuideFeatureEntry
import com.example.travelling.audio_guide.impl.AudioGuideFeatureImpl
import com.example.travelling.common.di.AppScope
import com.example.travelling.common.navigation.FeatureEntry
import com.example.travelling.common.navigation.FeatureEntryKey
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