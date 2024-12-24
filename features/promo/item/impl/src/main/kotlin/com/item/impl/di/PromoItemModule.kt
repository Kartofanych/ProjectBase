package com.item.impl.di

import com.item.impl.data.PromoItemApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
interface PromoItemModule {

    companion object {
        @Provides
        fun providePromoListApi(retrofit: Retrofit): PromoItemApi {
            return retrofit.create(PromoItemApi::class.java)
        }
    }
}
