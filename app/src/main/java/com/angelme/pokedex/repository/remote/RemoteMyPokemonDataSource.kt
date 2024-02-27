package com.angelme.pokedex.repository.remote

import com.angelme.pokedex.ui.model.Pokemon

interface RemoteMyPokemonDataSource {
    suspend fun getStoredPokemon(): List<Pokemon>
    suspend fun syncStorePokemon(pokemonList: List<Pokemon>)
}