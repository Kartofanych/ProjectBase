package com.inno.impl.di

import com.inno.api.GuideEntry
import com.inno.impl.GuideEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GuideNavigationModule {

    @Binds
    fun guideNavigation(guideEntry: GuideEntryImpl): GuideEntry
}