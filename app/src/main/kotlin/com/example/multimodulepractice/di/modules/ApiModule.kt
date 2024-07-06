package com.example.multimodulepractice.di.modules

import com.inno.impl.data.GuideApi
import com.inno.impl.data.network.MainApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @Provides
    fun provideGuideApi(retrofit: Retrofit): GuideApi {
        return retrofit.create(GuideApi::class.java)
    }

}