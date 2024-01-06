package com.example.multimodulepractice.di.modules

import com.example.impl.di.modules.AuthEntryModule
import dagger.Module


@Module(
    // ===== here add new feature entries =====
    includes = [
        AuthEntryModule::class,
    ]
)
interface NavigationModule