package com.river.pokedex.data.storage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.river.pokedex.data.storage.entity.PokemonDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM favorite_pokemon")
    fun observeAllFavorites(): Flow<List<PokemonDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(pokemon: PokemonDetailEntity)

    @Query("DELETE FROM favorite_pokemon WHERE id = :id")
    suspend fun deleteFavoriteById(id: Int)

    @Query("SELECT * FROM favorite_pokemon WHERE id = :id")
    suspend fun getFavoriteById(id: Int): PokemonDetailEntity?
}
