package com.example.multimodulepractice.common.data.models.local

enum class FilterDistance(val km: Double) {
    M500(0.5),
    M1000(1.0),
    M2000(2.0),
    M5000(5.0),
    EVERYWHERE(50000.0)
}