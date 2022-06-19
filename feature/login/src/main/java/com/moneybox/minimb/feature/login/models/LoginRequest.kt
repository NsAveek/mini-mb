package com.moneybox.minimb.feature.login.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("Idfa")
    val idfa: String = "",
    @SerializedName("DeviceIdentifier")
    val deviceIdentifier: String = "",
    @SerializedName("UniqueDeviceIdentifier")
    val uniqueDeviceId: String = "",
    @SerializedName("Email")
    val email: String,
    @SerializedName("Password")
    val password: String,
)