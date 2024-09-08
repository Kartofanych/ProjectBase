package com.main.common.data.local

data class LandmarkResponse(
    val id: String,
    val name: String,
    val info: String,
    val address: String,
    val images: List<String>,
    val categories: List<AttractionCategory>,
    val serviceGroups: List<ServiceGroup>
)