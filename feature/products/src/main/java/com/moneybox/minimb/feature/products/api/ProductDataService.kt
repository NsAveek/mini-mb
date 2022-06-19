package com.moneybox.minimb.feature.products.api


import com.moneybox.minimb.feature.products.domain.model.AllProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductDataService {

    @GET("investorproducts")
    suspend fun fetchPlanRequest() : Response<AllProductsResponse>
}