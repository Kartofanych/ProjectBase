package com.list.impl.di

import retrofit2.Retrofit
import javax.inject.Inject

class PromoListDependencies @Inject constructor(
    val retrofit: Retrofit
)
