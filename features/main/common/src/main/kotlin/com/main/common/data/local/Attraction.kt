package com.main.common.data.local

import com.example.travelling.common.data.models.local.AttractionCategory

data class Attraction(
    val id: String,
    val name: String,
    val distance: String,
    val icon: String,
    val shortInfo: String,
    val categories: List<AttractionCategory>,
    val dateCreation: String
)