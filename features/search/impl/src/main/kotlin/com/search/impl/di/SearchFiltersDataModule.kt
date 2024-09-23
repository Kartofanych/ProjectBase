package com.search.impl.di

import com.search_filters.api.data.domain.SearchFilterRepository
import com.search_filters.impl.data.repositories.SearchFilterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface SearchFiltersDataModule {

    @Binds
    @SearchScope
    @Reusable
    fun searchFiltersRepository(impl: SearchFilterRepositoryImpl): SearchFilterRepository
}