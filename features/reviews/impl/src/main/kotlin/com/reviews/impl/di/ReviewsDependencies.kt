package com.reviews.impl.di

import com.reviews.impl.data.ReviewsApi
import javax.inject.Inject

class ReviewsDependencies @Inject constructor(
    val reviewsApi: ReviewsApi,
)
