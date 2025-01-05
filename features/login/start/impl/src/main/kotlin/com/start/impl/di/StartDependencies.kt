package com.start.impl.di

import com.example.travelling.auth.AuthInfoManager
import javax.inject.Inject

class StartDependencies @Inject constructor(
    val authInfoManager: AuthInfoManager,
)
