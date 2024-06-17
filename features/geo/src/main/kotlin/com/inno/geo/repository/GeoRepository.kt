package com.inno.geo.repository

import com.example.common.models.GeoPoint
import com.inno.geo.models.GeoInfo
import kotlinx.coroutines.flow.Flow

interface GeoRepository {

    suspend fun updateGeoPoint(point: GeoPoint)

    suspend fun geoInfo(): GeoInfo

    fun geoInfoImmediately(): GeoInfo

    fun geoInfoFlow(): Flow<GeoInfo>
}