package com.service.impl.ui

sealed class ReviewModalState(
    val starsCount: Int = 5
) {

    object Loading : ReviewModalState()

    object Error : ReviewModalState()

    object Hidden : ReviewModalState()

    class Default(stars: Int) : ReviewModalState(stars)
}