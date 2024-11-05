package com.start.impl.ui

data class ProcessUiState(
    val currentText: String,
    val loginState: LoginState,
) {

    sealed interface LoginState {

        data class EmailState(val error: String?) : LoginState

        data class CodeState(
            val currentCode: Int = 0,
            val state: CodeScreenState = CodeScreenState.DEFAULT
        ) : LoginState {
            enum class CodeScreenState {
                ERROR,
                LOADING,
                DEFAULT
            }
        }
    }

    companion object {
        val EMPTY = ProcessUiState(
            currentText = "",
            loginState = LoginState.EmailState(null)
        )
    }
}
