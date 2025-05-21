package com.river.pokedex.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.river.pokedex.data.storage.entity.PokemonDetailEntity

@Database(
    entities = [PokemonDetailEntity::class],
    version = 1,
)
@TypeConverters(PokemonConverters::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun favoritePokemonDao(): PokemonDao
}
