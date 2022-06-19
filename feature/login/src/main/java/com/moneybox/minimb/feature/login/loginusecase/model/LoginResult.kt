package com.moneybox.minimb.feature.login.loginusecase.model

data class LoginResult(
    val emailError : AuthError? = null,
    val passwordError : AuthError? = null,
    val result : SimpleResource? = null
)
