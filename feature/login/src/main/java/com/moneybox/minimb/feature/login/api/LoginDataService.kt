package com.moneybox.minimb.feature.login.api

import com.moneybox.minimb.feature.login.domain.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginDataService {

    @POST("users/login")
    suspend fun loginRequest(@Body user : Map<String , String>) : Response<LoginResponse>
}