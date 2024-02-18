package com.angelme.pokedex.repository.local

import com.angelme.pokedex.ui.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun gotThemAll(): Flow<List<Pokemon>>
    fun getMyPokemonById(id: Int): Flow<Pokemon?>
    suspend fun gotPokemon(pokemon: Pokemon)

    suspend fun lostPokemon(pokemon: Pokemon)
}
