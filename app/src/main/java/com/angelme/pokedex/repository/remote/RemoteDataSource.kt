package com.angelme.pokedex.repository.remote

import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.ui.model.PokemonGeneration
import com.angelme.pokedex.util.Generation

interface RemoteDataSource {
    suspend fun getPokemonListByGeneration(generation: Generation): PokemonGeneration
    suspend fun getPokemon(index: Int): Pokemon
}