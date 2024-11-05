package com.example.multimodulepractice.di.modules

import com.example.multimodulepractice.common.di.AppScope
import com.example.multimodulepractice.network.HeaderInterceptor
import com.travelling.api.AppConfig
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
    @Named(HEADER_INTERCEPTOR)
    @AppScope
    fun bindHeaderInterceptor(interceptor: HeaderInterceptor): Interceptor

    companion object {

        private const val HEADER_INTERCEPTOR = "Header"
        private const val LOGGING_INTERCEPTOR = "Logging"

        private const val TESTING_BASE_URL =
            "https://bbalve6v55k081m4pfn9.containers.yandexcloud.net/"
        private const val PRODUCTION_BASE_URL =
            "https://bban3krd1de8vtafs5p3.containers.yandexcloud.net/"

        @Provides
        @AppScope
        fun provideBaseRetrofit(appConfig: AppConfig, client: OkHttpClient): Retrofit {
            val url = when {
                appConfig.isProduction() -> PRODUCTION_BASE_URL
                else -> TESTING_BASE_URL
            }

            return Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @AppScope
        fun provideHttpClient(
            @Named(LOGGING_INTERCEPTOR) loggingInterceptor: Interceptor,
            @Named(HEADER_INTERCEPTOR) headerInterceptor: Interceptor
        ): OkHttpClient =
            OkHttpClient.Builder().retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .build()

        @Provides
        @Named(LOGGING_INTERCEPTOR)
        fun getInterceptor(): Interceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return interceptor
        }
    }
}