package com.example.multimodulepractice.geo.repository

import com.example.multimodulepractice.common.models.local.GeoPoint
import com.example.multimodulepractice.geo.models.GeoInfo
import kotlinx.coroutines.flow.Flow

interface GeoRepository {

    suspend fun updateGeoPoint(point: GeoPoint)

    suspend fun geoInfo(): GeoInfo

    fun geoInfoImmediately(): GeoInfo

    fun geoInfoFlow(): Flow<GeoInfo>
}