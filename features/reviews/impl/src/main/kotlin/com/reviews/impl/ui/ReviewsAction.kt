package com.reviews.impl.ui

sealed interface ReviewsAction {
    data class OnScrollAction(val firstVisibleItem: Int) : ReviewsAction

    object OnReload : ReviewsAction

    object OnBackPressed : ReviewsAction
}
