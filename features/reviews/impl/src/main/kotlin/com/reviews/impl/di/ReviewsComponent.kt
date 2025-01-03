package com.reviews.impl.di

import com.example.multimodulepractice.common.data.models.network.ObjectType
import com.reviews.impl.ui.ReviewsViewModel
import dagger.BindsInstance
import dagger.Component

@ReviewsScope
@Component(
    dependencies = [ReviewsDependencies::class],
    modules = [ReviewsModule::class]
)
interface ReviewsComponent {

    val viewModel: ReviewsViewModel

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance data: ReviewsData,
            dependencies: ReviewsDependencies
        ): ReviewsComponent
    }

    class ReviewsData(
        val id: String,
        //TODO reviews for services
        val objectType: ObjectType,
    )
}
