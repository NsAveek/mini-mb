package com.moneybox.minimb.network.core

import com.moneybox.minimb.network.core.INetworkCoreDataService.Companion.API_VERSION
import com.moneybox.minimb.network.core.INetworkCoreDataService.Companion.APP_ID
import com.moneybox.minimb.network.core.INetworkCoreDataService.Companion.VERSION_NAME
import okhttp3.Interceptor
import okhttp3.Response

class NoAuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader(APP_ID_HEADER_NAME, APP_ID)
                .addHeader(API_VERSION_HEADER_NAME, API_VERSION)
                .addHeader(APP_VERSION_HEADER_NAME, VERSION_NAME)
                .addHeader(CONTENT_HEADER_NAME, CONTENT_HEADER_VALUE)
                .build()
        )
    }

    companion object {
        internal const val APP_ID_HEADER_NAME = "AppId"
        internal const val API_VERSION_HEADER_NAME = "ApiVersion"
        internal const val APP_VERSION_HEADER_NAME = "AppVersion"
        internal const val CONTENT_HEADER_NAME = "ContentDetail-Type"
        internal const val CONTENT_HEADER_VALUE = "application/json"
    }
}