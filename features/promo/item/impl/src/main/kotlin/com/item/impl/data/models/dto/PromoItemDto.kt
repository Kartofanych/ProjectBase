package com.item.impl.data.models.dto

import androidx.annotation.Keep
import com.example.multimodulepractice.common.data.models.network.ObjectType
import com.google.gson.annotations.SerializedName

@Keep
data class PromoItemDto(

    @SerializedName("object_uid")
    val id: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("qrcode")
    val code: String,

    @SerializedName("warning")
    val warning: String,

    @SerializedName("end_date")
    val endDate: String,

    @SerializedName("type")
    val type: ObjectType,
)
