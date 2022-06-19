package com.moneybox.minimb.feature.login.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("Session")
    val session: SessionDataResponse,
    @SerializedName("User")
    val user: UserResponse
)