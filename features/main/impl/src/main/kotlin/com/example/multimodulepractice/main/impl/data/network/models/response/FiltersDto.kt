package com.example.multimodulepractice.main.impl.data.network.models.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class FiltersDto(

    @SerializedName("categories")
    val categories: List<FiltersCategoryDto>
)