package com.example.travelling.di.modules

import com.attraction.impl.data.AttractionApi
import com.example.travelling.guide.impl.data.GuideApi
import com.main.common.data.MainApi
import com.service.impl.data.ServiceApi
import com.splash.impl.data.LaunchApi
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

    @Provides
    fun provideLaunchApi(retrofit: Retrofit): LaunchApi {
        return retrofit.create(LaunchApi::class.java)
    }

    @Provides
    fun provideServiceApi(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }

    @Provides
    fun provideAttractionApi(retrofit: Retrofit): AttractionApi {
        return retrofit.create(AttractionApi::class.java)
    }
}