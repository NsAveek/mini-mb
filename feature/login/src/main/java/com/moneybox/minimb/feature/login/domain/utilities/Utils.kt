package com.moneybox.minimb.feature.login.domain.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Utils {

    fun isNetworkAvailable(context : Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}