package com.river.pokedex.core.model

data class Page<T>(
    val hasNext: Boolean,
    val page: Int,
    val data: List<T>,
)
