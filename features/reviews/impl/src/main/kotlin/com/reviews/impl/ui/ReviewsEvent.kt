package com.reviews.impl.ui

sealed interface ReviewsEvent {
    object OnBack : ReviewsEvent
}
