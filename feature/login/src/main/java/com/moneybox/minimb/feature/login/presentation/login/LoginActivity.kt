package com.moneybox.minimb.feature.login.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.moneybox.minimb.feature.login.R
import com.moneybox.minimb.feature.login.databinding.ActivityLoginBinding
import com.moneybox.minimb.feature.login.domain.utilities.LoginActivityHandler
import com.moneybox.minimb.feature.login.domain.utilities.Utils.isNetworkAvailable
import com.moneybox.minimb.network.ApiResponseResult
import com.moneybox.minimb.network.ILogger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    @Inject
    lateinit var logger : ILogger
    private val loginViewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        with(binding){
            viewModel = loginViewModel
            handler = LoginActivityHandler(this)
        }
        setContentView(binding.root)
        logger.debug(message = "Log In View Created")

        observeViewModelResponse()

    }

    private fun observeViewModelResponse() {
        with(loginViewModel){
            loginClick.observe(this@LoginActivity, Observer {
                if (it){
                    if(this.isValid()) {
                        if (isNetworkAvailable(this@LoginActivity)) {
                            login()
                        } else {
                            Snackbar.make(
                                binding.root,
                                getString(R.string.no_internet),
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }else{
                        Snackbar.make(
                            binding.root,
                            getString(R.string.empty_fields),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            })
            loginResult.observe(this@LoginActivity, Observer{ response->

                when(response.status){
                    ApiResponseResult.Status.SUCCESS ->{
                        loginViewModel.storeLoginResponse(response.data)
                        binding.progressbarLogin.visibility = View.GONE
                    }
                    ApiResponseResult.Status.ERROR ->{
                        binding.progressbarLogin.visibility = View.GONE
                        Snackbar.make(binding.root, response.message.toString(), Snackbar.LENGTH_LONG).show()
                        logger.error(message = "Login Response Error", throwable = null)
                    }
                    ApiResponseResult.Status.LOADING ->{
                        binding.progressbarLogin.visibility = View.VISIBLE
                        logger.debug(message = "Login Response Loading")
                    }
                }
            })
            loginResultStored.observe(this@LoginActivity, Observer{ response ->
                if (response) {
                    binding.progressbarLogin.visibility = View.GONE
                    startActivity(
                        Intent("com.moneybox.minimb.feature.products.open")
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            .setPackage(this@LoginActivity.packageName)
                    )
                }
            })
        }
    }
}