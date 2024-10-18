package com.example.multimodulepractice.main.impl.di

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
    }
}