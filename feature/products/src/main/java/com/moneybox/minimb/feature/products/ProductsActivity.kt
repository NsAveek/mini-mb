package com.moneybox.minimb.feature.products

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.moneybox.minimb.feature.products.databinding.ActivityProductsBinding
import com.moneybox.minimb.feature.products.ui.ProductsViewModel
import com.moneybox.minimb.network.ApiResponseResult
import com.moneybox.minimb.network.ILogger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding
    @Inject
    lateinit var logger : ILogger

    private val productViewModel : ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductsBinding.inflate(layoutInflater)
        with(binding){
            viewModel = productViewModel
        }
        setContentView(binding.root)
        logger.debug(message = "Products View Created")

        observeViewModelReponse()

    }

    private fun observeViewModelReponse() {
        with(productViewModel){
            productFetchResult.observe(this@ProductsActivity, Observer { response ->
                when(response.status){
                    ApiResponseResult.Status.SUCCESS ->{
                        binding.tvPlanValue.text = response.data?.let {
                            it.totalPlanValue.toString()
                        }
                        binding.progressbarProducts.visibility = View.GONE
                    }
                    ApiResponseResult.Status.ERROR ->{
                        binding.progressbarProducts.visibility = View.GONE
                        Snackbar.make(binding.root, response.message.toString(), Snackbar.LENGTH_LONG).show()
                        logger.error(message = "Products Response Error", throwable = null)
                    }
                    ApiResponseResult.Status.LOADING ->{
                        binding.progressbarProducts.visibility = View.VISIBLE
                        logger.debug(message = "Products Response Loading")
                    }
                }
            })
        }
    }
}