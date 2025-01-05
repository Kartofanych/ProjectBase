package com.item.api

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.travelling.common.navigation.FeatureEntry

abstract class PromoItemEntry : FeatureEntry {


    override val featureRoute = "$BASE_ROUTE/{$ARG_ITEM_ID}"

    override val arguments = listOf(
        navArgument(ARG_ITEM_ID) {
            type = NavType.StringType
        }
    )

    fun destination(serviceId: String): String =
        "$BASE_ROUTE/$serviceId"

    protected companion object {
        const val BASE_ROUTE = "promo/item"
        const val ARG_ITEM_ID = "id"
    }
}
