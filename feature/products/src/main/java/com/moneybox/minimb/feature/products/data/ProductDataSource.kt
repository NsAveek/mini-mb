package com.moneybox.minimb.feature.products.data

import com.google.gson.Gson
import com.moneybox.minimb.feature.products.api.ProductDataService
import com.moneybox.minimb.feature.products.model.AllProductsResponse
import com.moneybox.minimb.network.ApiResponseResult
import com.moneybox.minimb.network.ErrorResponseRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductDataSource @Inject constructor(private val service : ProductDataService) {
    suspend fun login(userMap: HashMap<String, String>): Flow<ApiResponseResult<AllProductsResponse>> {
        return flow {
            emit(ApiResponseResult.loading())
            val result = service.loginRequest(userMap)
            if (result.isSuccessful) {
                emit(ApiResponseResult.success(data = result.body()))
            } else {
                val errorResponseRemote: ErrorResponseRemote =
                    Gson().fromJson(
                        result.errorBody()?.charStream(),
                        ErrorResponseRemote::class.java
                    )
                errorResponseRemote?.let {
                    it.message?.let { msg ->
                        emit(ApiResponseResult.error(msg, result.code().toString()))
                    }
                }
            }
        }
    }
    fun logout(){

    }
}