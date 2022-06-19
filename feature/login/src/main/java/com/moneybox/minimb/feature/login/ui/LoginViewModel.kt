package com.moneybox.minimb.feature.login.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moneybox.minimb.feature.login.data.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

//    private val _loginResult = MutableLiveData<ApiRe>
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult = _loginResult

    val _loginClick = MutableLiveData<Boolean>(false)
    val loginClick = _loginClick

    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    fun login(){
        _loginResult.value = true
    }
    fun triggerLogin(){
        _loginClick.value = true
    }
}