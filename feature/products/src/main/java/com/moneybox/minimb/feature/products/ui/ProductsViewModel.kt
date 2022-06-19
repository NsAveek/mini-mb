package com.moneybox.minimb.feature.products.ui

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneybox.minimb.feature.products.data.ProductRepository
import com.moneybox.minimb.feature.products.model.AllProductsResponse
import com.moneybox.minimb.network.ApiResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    private val _productFetchResult = MutableLiveData<ApiResponseResult<AllProductsResponse>>()
    val productFetchResult = _productFetchResult

    init {
        fetchProductResult()
    }

    private fun fetchProductResult() {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.fetchPlan().collect {
                withContext(Dispatchers.Main) {
                    _productFetchResult.value = it
                }
            }
        }
    }
}