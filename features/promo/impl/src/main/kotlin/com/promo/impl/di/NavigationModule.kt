package com.promo.impl.di

import com.item.impl.di.PromoItemNavigationModule
import com.list.impl.di.PromoListNavigationModule
import dagger.Module

@Module(
    includes = [
        PromoItemNavigationModule::class,
        PromoListNavigationModule::class
    ]
)
class NavigationModule
