package com.reviews.impl.di

import retrofit2.Retrofit
import javax.inject.Inject

class ReviewsDependencies @Inject constructor(
    val retrofit: Retrofit
)
