package com.example.multimodulepractice.di.modules

import com.example.multimodulepractice.common.di.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

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
    fun provideHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder().retryOnConnectionFailure(false)
            .addInterceptor(interceptor).build()

    @Provides
    fun getInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }
}