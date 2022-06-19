package com.moneybox.minimb.network.core

import android.content.SharedPreferences
import com.moneybox.minimb.network.NetworkConstants
import com.moneybox.minimb.network.core.INetworkCoreDataService.Companion.API_VERSION
import com.moneybox.minimb.network.core.INetworkCoreDataService.Companion.APP_ID
import com.moneybox.minimb.network.core.INetworkCoreDataService.Companion.VERSION_NAME
import com.moneybox.minimb.network.core.NoAuthenticationInterceptor.Companion.API_VERSION_HEADER_NAME
import com.moneybox.minimb.network.core.NoAuthenticationInterceptor.Companion.APP_ID_HEADER_NAME
import com.moneybox.minimb.network.core.NoAuthenticationInterceptor.Companion.APP_VERSION_HEADER_NAME
import com.moneybox.minimb.network.core.NoAuthenticationInterceptor.Companion.CONTENT_HEADER_NAME
import com.moneybox.minimb.network.core.NoAuthenticationInterceptor.Companion.CONTENT_HEADER_VALUE
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(val sharedPreferences: SharedPreferences): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val sessionManager = sharedPreferences.getString(NetworkConstants.AUTH_TOKEN.name, null)
//        val sessionManager = "w9NfHyRTz+MyT/NLKFNJGIiH/w0wO0O3CVrVgY5EYDA="

        val requestBuilder = chain.request().newBuilder()
            .addHeader(APP_ID_HEADER_NAME, APP_ID)
            .addHeader(API_VERSION_HEADER_NAME, API_VERSION)
            .addHeader(APP_VERSION_HEADER_NAME, VERSION_NAME)
            .addHeader(CONTENT_HEADER_NAME, CONTENT_HEADER_VALUE)
        sessionManager?.let {
            requestBuilder.addHeader(APP_AUTHORIZATION_HEADER_NAME, "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
    companion object {
        private const val APP_AUTHORIZATION_HEADER_NAME = "Authorization"
    }
}