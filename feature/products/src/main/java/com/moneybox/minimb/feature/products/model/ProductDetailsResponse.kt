package com.moneybox.minimb.feature.products.model

import com.google.gson.annotations.SerializedName

data class ProductDetailsResponse(
    @SerializedName("FriendlyName")
    val friendlyName: String
)