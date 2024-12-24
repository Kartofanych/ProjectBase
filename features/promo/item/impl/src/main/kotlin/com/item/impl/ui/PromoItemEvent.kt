package com.item.impl.ui

import com.example.multimodulepractice.common.data.models.network.ObjectType

sealed interface PromoItemEvent {
    class OnOpenInfo(val id: String, val type: ObjectType) : PromoItemEvent

    object OnBack : PromoItemEvent
}
