package com.example.travelling.login.impl.di

import com.start.impl.data.LoginProcessApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginApiModule {

    @Provides
    fun provideLoginApi(retrofit: Retrofit): LoginProcessApi {
        return retrofit.create(LoginProcessApi::class.java)
    }
}