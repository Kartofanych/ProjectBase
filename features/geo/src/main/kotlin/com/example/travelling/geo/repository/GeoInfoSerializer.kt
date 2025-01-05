package com.example.travelling.geo.repository

import androidx.datastore.core.Serializer
import com.example.travelling.geo.models.GeoInfoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

internal class GeoInfoSerializer : Serializer<GeoInfoDto> {
    override val defaultValue: GeoInfoDto
        get() = GeoInfoDto.EMPTY

    override suspend fun readFrom(input: InputStream): GeoInfoDto {
        return try {
            Json.decodeFromString(
                deserializer = GeoInfoDto.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.run { printStackTrace() }
            defaultValue
        }
    }

    override suspend fun writeTo(t: GeoInfoDto, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = GeoInfoDto.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}
