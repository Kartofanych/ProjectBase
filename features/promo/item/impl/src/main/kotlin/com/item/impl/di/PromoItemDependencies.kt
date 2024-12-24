package com.item.impl.di

import retrofit2.Retrofit
import javax.inject.Inject

class PromoItemDependencies @Inject constructor(
    val retrofit: Retrofit
)
