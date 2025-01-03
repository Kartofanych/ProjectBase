package com.reviews.impl.di

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.common.navigation.FeatureEntry
import com.example.multimodulepractice.common.navigation.FeatureEntryKey
import com.reviews.api.ReviewsEntry
import com.reviews.impl.ReviewsEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ReviewsNavigationModule {

    @Binds
    @AppScope
    @IntoMap
    @FeatureEntryKey(ReviewsEntry::class)
    fun reviewsEntry(entry: ReviewsEntryImpl): FeatureEntry
}
