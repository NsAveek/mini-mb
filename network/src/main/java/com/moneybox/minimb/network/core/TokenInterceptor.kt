package com.moneybox.minimb.network.core

import android.content.SharedPreferences
import com.moneybox.minimb.network.NetworkConstants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(val sharedPreferences: SharedPreferences): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val sessionManager = sharedPreferences.getString(NetworkConstants.AUTH_TOKEN.name, null)
//        val sessionManager = "w9NfHyRTz+MyT/NLKFNJGIiH/w0wO0O3CVrVgY5EYDA="

        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        sessionManager?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}