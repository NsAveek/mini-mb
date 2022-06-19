package com.moneybox.minimb.data.utility

import android.content.Context
import android.net.ConnectivityManager

class ConstantsClass {

    enum class Constants{
        LOG_IN_RESPONSE,
        IS_LOGGED_IN,
        AUTH_TOKEN,
        USER_NAME,
        BACKGROUND_LOCATION_PERMITTED,
        GET_PROFILE_RESPONSE,
        GET_ATTENDANCE_STATUS_RESPONSE
    }

    enum class ResponseCode{
        SUCCESS,
        ERROR,
        UNAUTHORIZED,
    }

}