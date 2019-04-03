package com.flyavis.firstkotlin.data.network

sealed class HttpResult<out T : Any> {
    class Success<out T : Any>(val data: T) : HttpResult<T>()
    class Error(val exception: Throwable) : HttpResult<Nothing>()
}