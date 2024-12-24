package com.list.impl.di

import com.list.impl.data.PromoListApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
interface PromoListModule {

    companion object {
        @Provides
        fun providePromoListApi(retrofit: Retrofit): PromoListApi {
            return retrofit.create(PromoListApi::class.java)
        }
    }
}
