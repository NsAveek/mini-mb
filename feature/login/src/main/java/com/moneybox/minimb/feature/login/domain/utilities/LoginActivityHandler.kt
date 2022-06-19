package com.moneybox.minimb.feature.login.domain.utilities

import android.text.TextUtils
import android.text.Editable
import android.util.Patterns
import com.moneybox.minimb.feature.login.R
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
                etEmail.error = binding.root.resources.getString(R.string.error_empty_email)
            } else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text!!.trim().toString()).matches()) {
                etEmail.error = binding.root.resources.getString(R.string.invalid_email_format)
            } else  {
                etEmail.error = null
            }
        }
    }
}