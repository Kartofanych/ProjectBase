package com.list.impl.data.models.local

data class PromoCodesResponse(
    val list: List<PromoCode>
)

data class PromoCode(
    val token: String,
    val title: String,
    val subtitle: String,
    val endDate: String,
    val imageUrl: String,
    val rating: Float,
    val tag: String,
)