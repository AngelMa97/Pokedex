package com.angelme.pokedex.ui.model

import com.angelme.pokedex.local.entity.PokemonEntity
import com.angelme.pokedex.util.PokemonType
import com.google.gson.Gson

class Pokemon(
    val abilities: List<String>,
    val id: Int,
    val name: String,
    val sprites: List<String>,
    val stats: List<Stat>,
    val types: List<PokemonType>,
    val gotIt: Boolean = false
) {
    fun pokemonToPokemonEntity(): PokemonEntity =
        PokemonEntity(
            id,
            Gson().toJson(abilities),
            name,
            Gson().toJson(sprites),
            Gson().toJson(stats),
            Gson().toJson(types),
            gotIt
        )
}

data class Stat(
    val name: String,
    val value: Int
)