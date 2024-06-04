package com.example.multimodulepractice.di.modules

import com.inno.impl.di.modules.MainEntryModule
import dagger.Module


@Module(
    // ===== here add new feature entries =====
    includes = [
        MainEntryModule::class
    ]
)
interface NavigationModule