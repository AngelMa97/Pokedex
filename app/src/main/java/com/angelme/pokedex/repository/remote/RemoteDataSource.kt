package com.angelme.pokedex.repository.remote

import com.angelme.pokedex.remote.PokemonGenerationResponse
import com.angelme.pokedex.remote.PokemonResponse

interface RemoteDataSource {
    suspend fun getPokemonListByGeneration(limit: Int, offset: Int): PokemonGenerationResponse
    suspend fun getPokemon(name: String): PokemonResponse
}