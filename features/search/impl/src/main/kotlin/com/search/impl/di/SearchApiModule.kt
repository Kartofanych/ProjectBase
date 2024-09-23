package com.search.impl.di

import com.search_filters.impl.data.SearchApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SearchApiModule {

    @Provides
    fun provideSearchApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }
}