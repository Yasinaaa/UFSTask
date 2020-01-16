package ru.android.ufstask.helper

import ru.android.ufstask.models.ErrorResponse

//sealed class Result<T>
//class Success<T>(val data: T) : Result<T>()
//class Error<T>(val exception: Throwable, val message: String = exception.localizedMessage) : Result<T>()

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
}