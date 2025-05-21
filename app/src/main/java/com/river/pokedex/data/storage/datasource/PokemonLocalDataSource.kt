package com.river.pokedex.data.storage.datasource

import com.river.pokedex.data.storage.database.PokemonDao
import com.river.pokedex.data.storage.entity.PokemonDetailEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonLocalDataSource @Inject constructor(
    private val dao: PokemonDao,
) {
    fun observeFavorites(): Flow<List<PokemonDetailEntity>> {
        return dao.observeAllFavorites()
    }

    suspend fun addFavorite(pokemon: PokemonDetailEntity) {
        dao.insertFavorite(pokemon)
    }

    suspend fun deleteFavoriteById(id: Int) {
        dao.deleteFavoriteById(id)
    }
}
