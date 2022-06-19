package com.moneybox.minimb.feature.products.data

import com.moneybox.minimb.feature.products.model.AllProductsResponse
import com.moneybox.minimb.network.ApiResponseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDataSource: ProductDataSource) {
    suspend fun fetchPlan(): Flow<ApiResponseResult<AllProductsResponse>> {
        return productDataSource.fetchPlanValue()
    }
}