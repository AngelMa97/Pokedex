package com.angelme.pokedex.ui.model

import com.angelme.pokedex.util.PokemonType

class Pokemon(
    val abilities: List<String>,
    val id: Int,
    val name: String,
    val sprites: List<String>,
    val stats: List<Stat>,
    val types: List<PokemonType>
)

data class Stat(
    val name: String,
    val value: Int
)