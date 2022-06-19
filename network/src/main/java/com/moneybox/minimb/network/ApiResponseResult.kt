package com.moneybox.minimb.network

data class ApiResponseResult<out T>(val status: Status, val data: T?, val error: String?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?, message: String? = ""): ApiResponseResult<T> {
            return ApiResponseResult(Status.SUCCESS, data, null, message)
        }

        fun <T> error(message: String, error: String?): ApiResponseResult<T> {
            return ApiResponseResult(Status.ERROR, null, error, message)
        }

        fun <T> loading(data: T? = null): ApiResponseResult<T> {
            return ApiResponseResult(Status.LOADING, data, null, null)
        }
    }

    override fun toString(): String {
        return "Result(status=$status, data=$data, error=$error, message=$message)"
    }
}