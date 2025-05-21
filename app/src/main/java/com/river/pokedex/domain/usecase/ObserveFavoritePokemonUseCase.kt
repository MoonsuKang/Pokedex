package com.river.pokedex.domain.usecase

import com.river.pokedex.domain.model.PokemonDetail
import com.river.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavoritePokemonUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    operator fun invoke(): Flow<List<PokemonDetail>> =
        repository.observeFavorites()
}
