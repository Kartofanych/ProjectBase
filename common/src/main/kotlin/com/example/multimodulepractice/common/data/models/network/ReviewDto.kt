package com.example.multimodulepractice.common.data.models.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ReviewDto(

    @SerializedName("uid")
    val id: String,

    @SerializedName("description")
    val text: String,

    @SerializedName("rate")
    val stars: Int,

    @SerializedName("date")
    val date: String,

    @SerializedName("author")
    val author: ReviewAuthor,
)

@Keep
data class ReviewAuthor(
    @SerializedName("uid")
    val id: String,

    @SerializedName("username")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("icon")
    val icon: String,
)

@Keep
data class RateListObjectDto(
    @SerializedName("1")
    val one: Int,

    @SerializedName("2")
    val two: Int,

    @SerializedName("3")
    val three: Int,

    @SerializedName("4")
    val four: Int,

    @SerializedName("5")
    val five: Int,
)
