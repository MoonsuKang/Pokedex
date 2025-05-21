package com.river.pokedex.data.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.river.pokedex.domain.model.PokemonDetail

@Entity(tableName = "favorite_pokemon")
data class PokemonDetailEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val height: Double,
    val weight: Double,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val spAttack: Int,
    val spDefense: Int,
    val speed: Int,
)

fun PokemonDetailEntity.toDomain(): PokemonDetail {
    return PokemonDetail(
        id = id,
        name = name,
        imageUrl = imageUrl,
        types = types,
        height = height,
        weight = weight,
        hp = hp,
        attack = attack,
        defense = defense,
        spAttack = spAttack,
        spDefense = spDefense,
        speed = speed,
    )
}

fun PokemonDetail.toEntity(): PokemonDetailEntity {
    return PokemonDetailEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        types = types,
        height = height,
        weight = weight,
        hp = hp,
        attack = attack,
        defense = defense,
        spAttack = spAttack,
        spDefense = spDefense,
        speed = speed,
    )
}
