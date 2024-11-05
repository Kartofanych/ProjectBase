package com.start.impl.data

import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.start.impl.data.mappers.ValidateMapper
import com.start.impl.data.models.dto.RegisterCall
import com.start.impl.data.models.dto.ValidationCall
import com.start.impl.data.models.local.ValidationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class LoginInteractor @Inject constructor(
    private val api: LoginProcessApi,
    private val validateMapper: ValidateMapper,
) {

    suspend fun register(email: String): ResponseState<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                api.register(RegisterCall(email))
                ResponseState.Success(Unit)
            } catch (e: Exception) {
                ResponseState.Error.Default()
            }
        }
    }

    suspend fun validateCode(email: String, code: String): ResponseState<ValidationResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val call = api.validateEmail(ValidationCall(email, code))
                ResponseState.Success(validateMapper.map(call))
            } catch (e: Exception) {
                when {
                    (e is HttpException && e.code() == 498) -> ResponseState.Error.BadCode()
                    else -> ResponseState.Error.Default()
                }
            }
        }
    }
}
