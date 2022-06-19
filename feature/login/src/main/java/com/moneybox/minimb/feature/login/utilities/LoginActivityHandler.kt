package com.moneybox.minimb.feature.login.utilities

import android.text.TextUtils
import android.text.Editable
import android.util.Patterns
import com.moneybox.minimb.feature.login.databinding.ActivityLoginBinding


class LoginActivityHandler (val binding : ActivityLoginBinding){
    fun passwordValidator(editable: Editable) {
        with(binding){
            if (etPassword == null) return
            val minimumLength = 5
            if (!TextUtils.isEmpty(editable.toString()) && editable.length < minimumLength) {
                etPassword.error = "Password must be minimum $minimumLength length"
            } else {
                etPassword.error = null
            }
        }
    }
    fun emailValidator(editable: Editable) {
        with(binding){
            if (etEmail.text!!.trim().toString().isEmpty()) {
//                etEmail.error = getResources().getString(R.string.email_error)
                etEmail.error = "Email can not be empty!"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text!!.trim().toString()).matches()) {
//                etEmail.error = getResources().getString(R.string.error_invalid_email);
                etEmail.error = "Invalid Email Format!"
            } else  {
                etEmail.error = null
            }
        }
    }
}