package com.service.impl.data.models.local

data class Service(
    val id: String,
    val title: String,
    val subtitle: String,
    val ratingBlock: RatingBlock,
    val price: String,
    val description: String,
    val images: List<String>,
    val organization: ServiceOrganization,
    val contacts: List<Contact>,
) {
    data class RatingBlock(
        val rating: Float,
        val reviewCount: String,
        val starCount: Int
    )

    data class ServiceOrganization(
        val id: String,
        val name: String,
        val icon: String,
        val rating: Float
    )

    data class Contact(
        val deeplink: String,
        val icon: String,
        val title: String,
    )
}
