package com.inno.geo.repository

import android.content.Context
import androidx.datastore.dataStore
import com.example.common.di.AppContext
import com.example.common.models.local.GeoPoint
import com.inno.geo.models.GeoInfo
import com.inno.geo.models.GeoInfoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeoRepositoryImpl @Inject constructor(
    @AppContext
    context: Context
) : GeoRepository {

    private val dataStore = context.dataStore

    override suspend fun updateGeoPoint(point: GeoPoint) {
        dataStore.updateData {
            it.copy(
                currentPoint = point.toGeoPointDto()
            )
        }
    }

    override suspend fun geoInfo(): GeoInfo {
        return dataStore.data.first().toGeoInfo()
    }

    override fun geoInfoImmediately(): GeoInfo {
        return runBlocking {
            dataStore.data.first().toGeoInfo()
        }
    }

    override fun geoInfoFlow(): Flow<GeoInfo> =
        dataStore.data.map(GeoInfoDto::toGeoInfo)

}


private val Context.dataStore by dataStore("geo-info.json", GeoInfoSerializer())