package com.wazz9p.core.base

sealed interface Result<out R> {

    data class Success<out T>(val data: T) : Result<T>
    data class Error(val e: Throwable) : Result<Nothing>
}