package com.attraction.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ServiceDto(

    @SerializedName("uid")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("rating")
    val rating: Float,

    @SerializedName("price")
    val price: String,

    @SerializedName("organization_uid")
    val organizationId: String,

    @SerializedName("is_icon_organization")
    val isIconOrganization: Boolean = true
)