package com.moneybox.minimb.feature.login.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneybox.minimb.feature.login.data.LoginRepository
import com.moneybox.minimb.feature.login.models.LoginResponse
import com.moneybox.minimb.network.ApiResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


/*Email: jaeren+androidtest@moneyboxapp.com
       Password: P455word12*/
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<ApiResponseResult<LoginResponse>>()
//    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult = _loginResult

    val _loginClick = MutableLiveData<Boolean>(false)
    val loginClick = _loginClick

    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    fun login(){
        val userMap = HashMap<String, String>()
        userMap["email"] = "jaeren+androidtest@moneyboxapp.com"
        userMap["password"] = "P455word12"

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
}