package com.start.impl.data

import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.example.travelling.common.utils.networkCall
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
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    api.register(RegisterCall(email))
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "register", time)

                    ResponseState.Success(Unit)
                },
                catch = { throwable ->
                    Analytics.reportNetworkError(route = "register", throwable = throwable)
                    ResponseState.Error.Default()
                }
            )
        }
    }

    suspend fun validateCode(email: String, code: String): ResponseState<ValidationResponse> {
        return withContext(Dispatchers.IO) {
            networkCall(
                run = {
                    val start = System.currentTimeMillis()
                    val call = api.validateEmail(ValidationCall(email, code))
                    val time = System.currentTimeMillis() - start
                    Analytics.reportNetworkSuccess(route = "validateCode", time)
                    ResponseState.Success(validateMapper.map(call))
                },
                catch = { throwable ->
                    when {
                        (throwable is HttpException && throwable.code() == 498) -> ResponseState.Error.BadCode()
                        else -> {
                            Analytics.reportNetworkError(
                                route = "validateCode",
                                throwable = throwable
                            )
                            ResponseState.Error.Default()
                        }
                    }
                }
            )
        }
    }
}
