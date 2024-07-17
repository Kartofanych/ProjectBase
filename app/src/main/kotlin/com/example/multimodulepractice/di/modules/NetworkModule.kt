package com.example.multimodulepractice.di.modules

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.network.HeaderInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
interface NetworkModule {

    @Binds
    @Named("Header")
    @AppScope
    fun bindHeaderInterceptor(interceptor: HeaderInterceptor): Interceptor

    companion object {
        @Provides
        @AppScope
        fun provideRetrofit(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .client(client)
                .baseUrl("http://91.224.86.138:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @AppScope
        fun provideHttpClient(
            @Named("Logging") loggingInterceptor: Interceptor,
            @Named("Header") headerInterceptor: Interceptor
        ): OkHttpClient =
            OkHttpClient.Builder().retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .build()

        @Provides
        @Named("Logging")
        fun getInterceptor(): Interceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return interceptor
        }
    }
}