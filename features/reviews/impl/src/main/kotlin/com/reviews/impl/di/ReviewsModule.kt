package com.reviews.impl.di

import com.reviews.impl.data.ReviewsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
interface ReviewsModule {

    companion object {
        @Provides
        fun provideReviewApi(retrofit: Retrofit): ReviewsApi {
            return retrofit.create(ReviewsApi::class.java)
        }
    }
}
