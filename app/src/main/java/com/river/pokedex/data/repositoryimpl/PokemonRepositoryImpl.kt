package com.river.pokedex.data.repositoryimpl

import com.river.pokedex.core.model.Page
import com.river.pokedex.data.datasource.PokemonRemoteDataSource
import com.river.pokedex.data.model.response.toPage
import com.river.pokedex.data.storage.datasource.PokemonLocalDataSource
import com.river.pokedex.data.storage.entity.toDomain
import com.river.pokedex.data.storage.entity.toEntity
import com.river.pokedex.domain.model.PokemonDetail
import com.river.pokedex.domain.model.PokemonListItem
import com.river.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource,
) : PokemonRepository {

    override suspend fun getPokemonPage(page: Int, pageSize: Int): Page<PokemonListItem> {
        val offset = (page - 1) * pageSize
        return remoteDataSource.getPokemonList(offset, pageSize).toPage(page)
    }

    override suspend fun getPokemonDetail(name: String): PokemonDetail =
        remoteDataSource.getPokemonDetail(name).toDomain()

    override fun observeFavorites(): Flow<List<PokemonDetail>> {
        return localDataSource.observeFavorites()
            .map { list -> list.map { it.toDomain() } }
    }

    override suspend fun addFavorite(pokemon: PokemonDetail) =
        localDataSource.addFavorite(pokemon.toEntity())

    override suspend fun deleteFavoriteById(id: Int) =
        localDataSource.deleteFavoriteById(id)
}
