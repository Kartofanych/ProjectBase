package com.attraction.impl.ui

import com.example.travelling.common.data.models.network.ObjectType

sealed interface AttractionAction {

    object RecallAttraction : AttractionAction

    object OpenGuide : AttractionAction

    object OnBackPressed : AttractionAction

    object OnLikeChanged : AttractionAction

    object OpenOnMap : AttractionAction

    object OpenReviews : AttractionAction

    object ChangeScheduleVisibility : AttractionAction

    class OpenObject(val id: String, val type: ObjectType) : AttractionAction

    class SendReview(val text: String) : AttractionAction

    class ChangeReviewModalVisibility(val visible: Boolean) : AttractionAction

    class ChangeReviewStars(val starsCount: Int) : AttractionAction
}