package com.example.travelling.di

import android.app.Activity
import com.example.travelling.common.di.ActivityScope
import com.example.travelling.common.domain.DeeplinkHandler
import com.example.travelling.common.navigation.Destinations
import com.example.travelling.di.modules.ActivityModule
import com.example.travelling.di.modules.NavigationModule
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