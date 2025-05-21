package com.river.pokedex.domain.usecase

sealed interface AddFavoriteResult {
    data object Success : AddFavoriteResult
    data object AlreadyExists : AddFavoriteResult
    data object LimitExceeded : AddFavoriteResult
}
