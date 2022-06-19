package com.moneybox.minimb.feature.login.domain.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("UserId")
    val userId: String,
    @SerializedName("FirstName")
    val firstName: String,
    @SerializedName("LastName")
    val lastName: String,
    @SerializedName("Email")
    val email: String
)