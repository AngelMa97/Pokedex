package com.angelme.pokedex.repository

import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.ui.model.PokemonGeneration
import com.angelme.pokedex.util.Generation

interface PokemonRepository {
    suspend fun getPokemonByGeneration(generation: Generation): PokemonGeneration
    suspend fun getPokemon(index: Int): Pokemon
}