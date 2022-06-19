package com.moneybox.minimb.feature.login.presentation.login

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneybox.minimb.feature.login.data.LoginRepository
import com.moneybox.minimb.feature.login.domain.models.LoginResponse
import com.moneybox.minimb.network.ApiResponseResult
import com.moneybox.minimb.network.NetworkConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


/*Email: jaeren+androidtest@moneyboxapp.com
       Password: P455word12*/
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository, private val sharedPreferences: SharedPreferences) : ViewModel() {

    private val _loginResult = MutableLiveData<ApiResponseResult<LoginResponse>>()
    val loginResult = _loginResult

    val _loginClick = MutableLiveData<Boolean>(false)
    val loginClick = _loginClick

    val _loginResultStored = MutableLiveData<Boolean>(false)
    val loginResultStored = _loginResultStored

    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    fun login(){
        val userMap = HashMap<String, String>()
        userMap["email"] = email.value!!
        userMap["password"] = password.value!!

        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.login(userMap).collect {
                withContext(Dispatchers.Main) {
                    _loginResult.value = it
                }
            }
        }
    }
    fun triggerLogin(){
        _loginClick.value = true
    }
    fun storeLoginResponse(response: LoginResponse?){
        response?.let {
            try {
                with(sharedPreferences){
                    val prefEditor = this.edit()
                    with(prefEditor){
                        putBoolean(NetworkConstants.IS_LOGGED_IN.name, true)
                        response.session?.let {
                            putString(NetworkConstants.AUTH_TOKEN.name, it.bearerToken)
                        }
                        apply()
                    }
                }
                _loginResultStored.value = true
            } catch (e: Exception) {
                _loginResultStored.value = false
            }
        } ?: kotlin.run {
            _loginResultStored.value = false
        }
    }
}