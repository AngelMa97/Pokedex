package com.angelme.pokedex.repository

import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.ui.model.PokemonGeneration
import com.angelme.pokedex.util.Generation
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonByGeneration(generation: Generation): PokemonGeneration
    suspend fun getPokemon(index: Int): Pokemon
    fun gotThemAll(): Flow<List<Pokemon>>
    fun getMyPokemonById(id: Int): Flow<Pokemon?>
    suspend fun gotPokemon(pokemon: Pokemon)
    suspend fun lostPokemon(pokemon: Pokemon)
}