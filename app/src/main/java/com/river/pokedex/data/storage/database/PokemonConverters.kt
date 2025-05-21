package com.river.pokedex.data.storage.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

object PokemonConverters {
    @TypeConverter
    fun fromList(types: List<String>): String = Json.encodeToString(types)

    @TypeConverter
    fun toList(types: String): List<String> = Json.decodeFromString(types)
}
