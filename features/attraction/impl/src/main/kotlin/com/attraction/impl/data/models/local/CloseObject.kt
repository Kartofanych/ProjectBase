package com.attraction.impl.data.models.local

import com.example.multimodulepractice.common.data.models.network.ObjectType

data class CloseObject(
    val id: String,
    val title: String,
    val subtitle: String,
    val icon: String,
    val rating: Float,
    val starsCount: Int,
    val distance: String,
    val type: ObjectType,
)