package com.example.multimodulepractice.di.modules

import com.example.multimodulepractice.di.modules.NetworkModule.Companion.YANDEX_RETROFIT
import com.example.multimodulepractice.guide.impl.data.GuideApi
import com.example.multimodulepractice.login.impl.data.AuthApi
import com.example.multimodulepractice.main.impl.data.network.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

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

    @Provides
    fun provideAuthApi(@Named(YANDEX_RETROFIT) retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

}