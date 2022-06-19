package com.moneybox.minimb.feature.login.models

import com.google.gson.annotations.SerializedName

data class SessionDataResponse(
    @SerializedName("BearerToken")
    val bearerToken: String
)