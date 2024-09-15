package com.attraction.impl.data.models.local

data class Service(
    val id: String,
    val title: String,
    val subtitle: String,
    val icon: String,
    val rating: Float,
    val price: String,
    val organizationId: String,
    val isIconOrganization: Boolean
)