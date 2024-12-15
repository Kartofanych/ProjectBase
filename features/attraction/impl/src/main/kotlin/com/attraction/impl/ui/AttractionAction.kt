package com.attraction.impl.ui

sealed interface AttractionAction {

    object RecallAttraction : AttractionAction

    object OpenGuide : AttractionAction

    object OnBackPressed : AttractionAction

    object OnLikeChanged : AttractionAction

    object OpenOnMap : AttractionAction

    object ChangeScheduleVisibility : AttractionAction

    class OpenService(val serviceId: String) : AttractionAction

    class SendReview(val text: String) : AttractionAction

    class ChangeReviewModalVisibility(val visible: Boolean) : AttractionAction

    class ChangeReviewStars(val starsCount: Int) : AttractionAction
}