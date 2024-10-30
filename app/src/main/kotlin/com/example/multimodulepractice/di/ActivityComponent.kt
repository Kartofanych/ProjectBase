package com.example.multimodulepractice.di

import android.app.Activity
import com.example.multimodulepractice.common.di.ActivityScope
import com.example.multimodulepractice.common.domain.DeeplinkHandler
import com.example.multimodulepractice.common.navigation.Destinations
import com.example.multimodulepractice.di.modules.ActivityModule
import com.example.multimodulepractice.di.modules.NavigationModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class, NavigationModule::class])
@ActivityScope
interface ActivityComponent {

    val deeplinkHandler: DeeplinkHandler

    val destinations: Destinations

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): ActivityComponent
    }
}