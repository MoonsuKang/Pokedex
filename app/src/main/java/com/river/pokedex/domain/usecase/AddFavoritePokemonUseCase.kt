package com.river.pokedex.domain.usecase

import com.river.pokedex.domain.model.PokemonDetail
import com.river.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AddFavoritePokemonUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    companion object {
        private const val MAX_FAVORITE_SIZE = 10
    }

    suspend operator fun invoke(pokemon: PokemonDetail): AddFavoriteResult {
        val favorites = repository.observeFavorites().first()

        return when {
            favorites.any { it.id == pokemon.id } -> AddFavoriteResult.AlreadyExists
            favorites.size >= MAX_FAVORITE_SIZE -> AddFavoriteResult.LimitExceeded
            else -> {
                repository.addFavorite(pokemon)
                AddFavoriteResult.Success
            }
        }
    }
}
