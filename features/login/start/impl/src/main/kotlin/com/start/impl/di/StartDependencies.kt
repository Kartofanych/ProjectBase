package com.start.impl.di

import com.example.multimodulepractice.auth.AuthInfoManager
import javax.inject.Inject

class StartDependencies @Inject constructor(
    val authInfoManager: AuthInfoManager,
)
