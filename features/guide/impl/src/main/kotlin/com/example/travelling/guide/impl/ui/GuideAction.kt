package com.example.travelling.guide.impl.ui

sealed interface GuideAction {

    object OnBackPressed : GuideAction

    object OnPreviousPage : GuideAction

    object OnNextPage : GuideAction

}