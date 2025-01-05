package com.example.travelling.geo.repository

import com.example.travelling.common.data.models.local.GeoPoint
import com.example.travelling.geo.models.GeoInfo
import kotlinx.coroutines.flow.Flow

interface GeoRepository {

    suspend fun updateGeoPoint(point: GeoPoint)

    suspend fun geoInfo(): GeoInfo

    fun geoInfoImmediately(): GeoInfo

    fun geoInfoFlow(): Flow<GeoInfo>
}