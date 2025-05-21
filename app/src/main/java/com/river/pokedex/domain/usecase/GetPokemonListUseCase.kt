package com.river.pokedex.domain.usecase

import com.river.pokedex.core.model.Page
import com.river.pokedex.domain.model.PokemonListItem
import com.river.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    suspend operator fun invoke(page: Int, pageSize: Int): Page<PokemonListItem> {
        return repository.getPokemonPage(page, pageSize)
    }
}
