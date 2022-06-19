package com.moneybox.minimb.feature.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moneybox.minimb.network.ILogger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {
    @Inject
    lateinit var logger : ILogger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        logger.debug(message = "Products View Created")
    }
}