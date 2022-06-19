package com.moneybox.minimb.network


interface ILogger {

    fun error(tag: String? = null, message: String?, throwable: Throwable?)

    fun info(tag: String? = null, message: String)

    fun debug(tag: String? = null, message: String)

    fun warn(tag: String? = null, message: String)

    fun verbose(tag: String? = null, message: String)
}