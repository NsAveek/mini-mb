package com.moneybox.minimb.feature.products.data

import com.moneybox.minimb.feature.products.model.AllProductsResponse
import com.moneybox.minimb.network.ApiResponseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(private val loginDataSource: ProductDataSource) {
    suspend fun login(userMap : HashMap<String, String>): Flow<ApiResponseResult<AllProductsResponse>> {
        return loginDataSource.login(userMap)
    }
    fun logout() {
//        user = null
        loginDataSource.logout()
    }
}