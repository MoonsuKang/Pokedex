package com.river.pokedex.domain.repository

import com.river.pokedex.core.model.Page
import com.river.pokedex.domain.model.PokemonDetail
import com.river.pokedex.domain.model.PokemonListItem
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonPage(page: Int, pageSize: Int): Page<PokemonListItem>
    suspend fun getPokemonDetail(name: String): PokemonDetail
    fun observeFavorites(): Flow<List<PokemonDetail>>
    suspend fun addFavorite(pokemon: PokemonDetail)
    suspend fun deleteFavoriteById(id: Int)
}
