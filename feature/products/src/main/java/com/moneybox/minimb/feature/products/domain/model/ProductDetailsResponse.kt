package com.moneybox.minimb.feature.products.domain.model

import com.google.gson.annotations.SerializedName

data class ProductDetailsResponse(
    @SerializedName("FriendlyName")
    val friendlyName: String
)