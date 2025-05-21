package com.river.pokedex.domain.usecase

import com.river.pokedex.domain.model.PokemonDetail
import com.river.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    suspend operator fun invoke(name: String): PokemonDetail =
        repository.getPokemonDetail(name)
}
