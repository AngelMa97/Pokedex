package com.angelme.pokedex.repository

import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.ui.model.PokemonGeneration

interface PokemonRepository {
    suspend fun getPokemonByGeneration(limit: Int, offset: Int): PokemonGeneration
    suspend fun getPokemon(name: String): Pokemon
}