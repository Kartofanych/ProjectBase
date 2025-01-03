package com.reviews.impl.data.models.local

import com.example.multimodulepractice.common.data.models.local.Review

class ReviewsListResponse(
    val items: List<Review>,
    val cursor: String,
)
