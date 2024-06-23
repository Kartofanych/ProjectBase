package com.inno.impl.data.local.models

data class Landmark(
    val imageRes: List<String>,
    val imageForThumbNail: String,
    val name: String,
    val address: String,
    val description: String,
    val categories: List<Categories>
)