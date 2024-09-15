package com.attraction.impl.data.models.local

import com.example.multimodulepractice.common.data.models.local.AttractionCategory

data class Attraction(
    val id: String,
    val name: String,
    val info: String,
    val address: String,
    val images: List<String>,
    val categories: List<AttractionCategory>,
    val serviceGroups: List<ServiceGroup>
)