package com.example.multimodulepractice.login.impl.di

import android.content.Context
import com.example.multimodulepractice.auth.AuthInfoManager
import com.example.multimodulepractice.common.di.AppContext
import retrofit2.Retrofit
import javax.inject.Inject

class LoginDependencies @Inject constructor(
    @AppContext
    val context: Context,
    val authInfoManager: AuthInfoManager,
    val retrofit: Retrofit,
)
