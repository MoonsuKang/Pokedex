package com.river.pokedex.domain.usecase

import com.river.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class DeleteFavoritePokemonUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    suspend operator fun invoke(id: Int) = repository.deleteFavoriteById(id)
}
