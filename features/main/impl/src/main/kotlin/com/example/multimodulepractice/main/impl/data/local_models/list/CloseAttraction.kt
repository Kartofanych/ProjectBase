package com.example.multimodulepractice.main.impl.data.local_models.list

import com.example.multimodulepractice.landmark.data.AttractionCategory

data class CloseAttraction(
    val id: String,
    val name: String,
    val distance: String,
    val icon: String,
    val shortInfo: String,
    val categories: List<AttractionCategory>,
    val dateCreation: String
)