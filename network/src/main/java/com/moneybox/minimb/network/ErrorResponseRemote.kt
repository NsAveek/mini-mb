package com.moneybox.minimb.network

import com.google.gson.annotations.SerializedName


data class ErrorResponseRemote (

  @SerializedName("success" ) var success : Boolean? = null,
  @SerializedName("message" ) var message : String?  = null

)