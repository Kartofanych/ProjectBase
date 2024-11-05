package com.start.impl.di

import com.example.multimodulepractice.auth.AuthInfoManager
import com.start.impl.data.LoginProcessApi
import javax.inject.Inject

class ProcessDependencies @Inject constructor(
    val loginProcessApi: LoginProcessApi,
    val authInfoManager: AuthInfoManager,
)
