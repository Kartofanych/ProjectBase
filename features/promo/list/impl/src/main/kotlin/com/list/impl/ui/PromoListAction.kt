package com.list.impl.ui

sealed interface PromoListAction {
    object Close : PromoListAction

    object OnReload : PromoListAction

    class OnOpenPromoItem(val id: String) : PromoListAction
}
