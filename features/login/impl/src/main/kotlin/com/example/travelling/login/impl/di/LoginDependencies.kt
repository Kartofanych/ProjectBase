package com.example.travelling.login.impl.di

import android.content.Context
import com.example.travelling.auth.AuthInfoManager
import com.example.travelling.common.di.AppContext
import retrofit2.Retrofit
import javax.inject.Inject

class LoginDependencies @Inject constructor(
    @AppContext
    val context: Context,
    val authInfoManager: AuthInfoManager,
    val retrofit: Retrofit,
)
