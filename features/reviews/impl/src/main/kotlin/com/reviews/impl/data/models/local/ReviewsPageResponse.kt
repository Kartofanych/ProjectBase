package com.reviews.impl.data.models.local

import com.example.travelling.common.data.models.local.RatingBlock
import com.example.travelling.common.data.models.local.Review

class ReviewsPageResponse(
    val title: String,
    val cursor: String?,
    val items: List<Review>,
    val rateList: List<Int>,
    val ratingBlock: RatingBlock
)
