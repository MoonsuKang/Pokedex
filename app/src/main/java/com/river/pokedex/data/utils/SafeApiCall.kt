package com.river.pokedex.data.utils

import retrofit2.HttpException

internal inline fun <T> safeApiCall(action: () -> T): Result<T> =
    runCatching(action).recoverCatching { exception ->
        when (exception) {
            is HttpException -> throw mapHttpException(exception)
            else -> throw exception
        }
    }

private fun mapHttpException(exception: HttpException): ApiError {
    return when (exception.code()) {
        404 -> ApiError("포켓몬을 찾을 수 없습니다.")
        else -> ApiError("서버 오류가 발생했습니다.")
    }
}
