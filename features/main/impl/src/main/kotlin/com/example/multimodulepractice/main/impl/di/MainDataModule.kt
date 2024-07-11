package com.example.multimodulepractice.main.impl.di

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.main.impl.repositories.AttractionRepository
import com.example.multimodulepractice.main.impl.repositories.AttractionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface MainDataModule {

    @Binds
    @AppScope
    @Reusable
    fun bindAttractionRepository(attractionRepositoryImpl: AttractionRepositoryImpl): AttractionRepository

}