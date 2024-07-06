package com.example.impl.auth_info

import androidx.datastore.core.Serializer
import com.example.impl.auth_info.models.AuthInfoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class AuthInfoSerializer : Serializer<AuthInfoDto> {
    override val defaultValue: AuthInfoDto
        get() = AuthInfoDto.EMPTY

    override suspend fun readFrom(input: InputStream): AuthInfoDto {
        return try {
            Json.decodeFromString(
                deserializer = AuthInfoDto.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.run { printStackTrace() }
            defaultValue
        }
    }

    override suspend fun writeTo(t: AuthInfoDto, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = AuthInfoDto.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}