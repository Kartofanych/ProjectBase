package com.item.impl.ui

import com.example.travelling.common.data.models.network.ObjectType

sealed interface PromoItemAction {
    class OpenInfo(val id: String, val type: ObjectType) : PromoItemAction

    object Close : PromoItemAction
}
