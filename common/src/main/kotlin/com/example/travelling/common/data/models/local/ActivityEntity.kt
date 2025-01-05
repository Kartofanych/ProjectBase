package com.example.travelling.common.data.models.local

data class ActivityEntity(
    val id: String,
    val icon: String,
    val tag: String,
    val type: ActivityType,
    val title: String,
    val subtitle: String,
    val description: String,
    val starCount: Int,
    val rating: Float
) {
    enum class ActivityType {
        LANDMARK,
        SERVICE
    }
}
