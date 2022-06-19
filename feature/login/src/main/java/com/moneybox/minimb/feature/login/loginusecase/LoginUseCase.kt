package com.moneybox.minimb.feature.login.loginusecase

import com.moneybox.minimb.feature.login.data.LoginRepository
import com.moneybox.minimb.feature.login.loginusecase.model.AuthError
import com.moneybox.minimb.feature.login.loginusecase.model.LoginResult
import javax.inject.Inject

class LoginUseCase @Inject constructor( val loginRepository : LoginRepository) {
    suspend operator fun invoke(
        email : String, password: String
    ) : LoginResult {
        val emailError = if(email.isBlank()) AuthError.FieldEmpty else null
        val passwordError = if(password.isBlank()) AuthError.FieldEmpty else null
        if(emailError != null || passwordError != null) {
            return LoginResult(emailError, passwordError)
        }
        return LoginResult(
            result = loginRepository.login(email, password)
        )
    }
}