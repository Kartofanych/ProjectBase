package com.service.impl.ui

sealed interface ServiceAction {
    class Deeplink(val deeplink: String) : ServiceAction

    class ChangeReviewModalVisibility(val visible: Boolean) : ServiceAction

    class ChangeReviewStars(val starCount: Int) : ServiceAction

    class SendReview(val text: String) : ServiceAction

    object OnBackPressed : ServiceAction

    object OnReload : ServiceAction

    object ChangeScheduleVisibility : ServiceAction

    object OpenReviews : ServiceAction

    object OpenOnMap : ServiceAction

    object ChangeCallBottomSheetVisibility : ServiceAction
}