package com.example.multimodulepractice.di.modules

import com.example.multimodulepractice.guide.impl.data.GuideApi
import com.example.multimodulepractice.main.impl.data.network.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
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