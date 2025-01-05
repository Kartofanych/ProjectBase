package com.example.travelling.main.impl.di

import com.favourites.impl.data.FavoritesApi
import com.search.impl.data.MainListApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
interface MainBindsModule {

    companion object {
        @Provides
        fun provideAttractionApi(retrofit: Retrofit): MainListApi {
            return retrofit.create(MainListApi::class.java)
        }

        @Provides
        fun provideFavoritesApi(retrofit: Retrofit): FavoritesApi {
            return retrofit.create(FavoritesApi::class.java)
        }
    }
}