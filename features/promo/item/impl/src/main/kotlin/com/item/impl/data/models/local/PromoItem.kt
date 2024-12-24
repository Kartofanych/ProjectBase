package com.item.impl.data.models.local

import android.graphics.Bitmap
import com.example.multimodulepractice.common.data.models.network.ObjectType

data class PromoItem(
    val name: String,
    val description: String,
    val code: Bitmap,
    val warning: String,
    val endDate: String,
    val type: ObjectType,
    val imageUrl: String,
    val id: String,
)