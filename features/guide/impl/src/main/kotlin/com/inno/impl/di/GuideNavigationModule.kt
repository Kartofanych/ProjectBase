package com.inno.impl.di

import com.inno.api.GuideEntry
import com.inno.impl.GuideEntryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class GuideNavigationModule {

    @Provides
    fun guideNavigation(): GuideEntry {
        return GuideEntryImpl()
    }
}