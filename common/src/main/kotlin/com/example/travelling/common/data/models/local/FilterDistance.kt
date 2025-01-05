package com.example.travelling.common.data.models.local

enum class FilterDistance(val km: Double) {
    M500(0.5),
    M1000(1.0),
    M2000(2.0),
    M5000(5.0),
    EVERYWHERE(50000.0);

    companion object {
        fun fromFiltersValue(value: Float): FilterDistance {
            return when (value) {
                0f -> M500
                100f -> M1000
                200f -> M2000
                300f -> M5000
                else -> EVERYWHERE
            }
        }

        fun FilterDistance.toFiltersValue(): Float {
            return when (this) {
                M500 -> 0f
                M1000 -> 100f
                M2000 -> 200f
                M5000 -> 300f
                EVERYWHERE -> 400f
            }
        }
    }
}