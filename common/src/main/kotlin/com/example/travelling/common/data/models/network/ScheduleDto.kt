package com.example.travelling.common.data.models.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ScheduleDto(
    @SerializedName("status")
    val status: StatusDto,

    @SerializedName("days")
    val days: List<DayScheduleDto>
) {
    @Keep
    data class StatusDto(
        @SerializedName("is_open")
        val isOpen: Boolean,

        @SerializedName("subtitle")
        val subtitle: String,
    )

    @Keep
    data class DayScheduleDto(
        @SerializedName("name")
        val name: String,

        @SerializedName("timing")
        val timing: String,
    )
}