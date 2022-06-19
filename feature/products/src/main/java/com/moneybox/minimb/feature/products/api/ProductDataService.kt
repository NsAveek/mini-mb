package com.moneybox.minimb.feature.products.api


import com.moneybox.minimb.feature.products.model.AllProductsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductDataService {

    @GET("investorproducts")
    suspend fun loginRequest(@Body user : Map<String , String>) : Response<AllProductsResponse>
}