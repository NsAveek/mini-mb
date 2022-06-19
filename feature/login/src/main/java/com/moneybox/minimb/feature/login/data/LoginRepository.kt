package com.moneybox.minimb.feature.login.data

import com.bumptech.glide.load.resource.SimpleResource
import com.moneybox.minimb.feature.login.domain.models.LoginResponse
import com.moneybox.minimb.network.ApiResponseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginDataSource: LoginDataSource) {
    suspend fun login(userMap : HashMap<String, String>): SimpleResource {
        return loginDataSource.login(userMap)
    }
    fun logout() {
        loginDataSource.logout()
    }
}