package com.angelme.pokedex.repository.local

import com.angelme.pokedex.local.AppDatabase
import com.angelme.pokedex.ui.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomDataSource @Inject constructor(
    private val appDatabase: AppDatabase
) : LocalDataSource {
    override fun gotThemAll(): Flow<List<Pokemon>> =
        appDatabase.pokemonDao().gotThemAll().map {x ->
            x.map { it.pokemonEntityToPokemon() }
        }

    override fun getMyPokemonById(id: Int): Flow<Pokemon?> =
        appDatabase.pokemonDao().getPokemonById(id).map {
            it?.pokemonEntityToPokemon()
        }

    override suspend fun gotPokemon(pokemon: Pokemon) =
        appDatabase.pokemonDao().gotPokemon(pokemon.pokemonToPokemonEntity())

    override suspend fun lostPokemon(pokemon: Pokemon) =
        appDatabase.pokemonDao().lostPokemon(pokemon.pokemonToPokemonEntity())
}