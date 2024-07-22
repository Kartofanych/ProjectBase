package com.example.multimodulepractice.main.impl.di

import com.example.multimodulepractice.main.impl.repositories.AttractionRepository
import com.example.multimodulepractice.main.impl.repositories.AttractionRepositoryImpl
import com.example.multimodulepractice.main.impl.repositories.RecommendedAttractionsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
interface MainDataModule {

    @Binds
    @MainScope
    @Reusable
    fun bindAttractionRepository(attractionRepositoryImpl: AttractionRepositoryImpl): AttractionRepository

    companion object {
        @Provides
        @MainScope
        @Reusable
        fun recommendedAttractionsRepository(): RecommendedAttractionsRepository {
            return RecommendedAttractionsRepository()
        }
    }

}