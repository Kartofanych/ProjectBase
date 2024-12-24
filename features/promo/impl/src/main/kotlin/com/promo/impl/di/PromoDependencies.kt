package com.promo.impl.di

import retrofit2.Retrofit
import javax.inject.Inject

class PromoDependencies @Inject constructor(
    val retrofit: Retrofit
)