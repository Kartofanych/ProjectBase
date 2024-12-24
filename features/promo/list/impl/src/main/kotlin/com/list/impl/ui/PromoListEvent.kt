package com.list.impl.ui

sealed interface PromoListEvent {
    object OnClose : PromoListEvent

    class OnOpenPromo(val id: String) : PromoListEvent
}
