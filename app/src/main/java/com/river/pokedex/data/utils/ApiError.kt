package com.river.pokedex.data.utils

data class ApiError(
    override val message: String,
) : Exception()
