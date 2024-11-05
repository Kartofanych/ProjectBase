package com.start.impl.ui

sealed interface ProcessAction {
    data class ChangeMailText(val text: String) : ProcessAction

    data class ChangeCodeText(val code: String) : ProcessAction

    data class CodeTextTapped(val index: Int) : ProcessAction

    object NextPressed : ProcessAction

    object OnClose : ProcessAction

    object ResendCode : ProcessAction
}
