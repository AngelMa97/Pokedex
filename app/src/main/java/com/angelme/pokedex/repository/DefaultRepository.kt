package com.angelme.pokedex.repository

import com.angelme.pokedex.repository.remote.RemoteDataSource
import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.ui.model.PokemonGeneration
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    //private val dataSource: RemoteDataSource
) /*: PokemonRepository*/ {
    /*override suspend fun getPokemonByGeneration(limit: Int, offset: Int): PokemonGeneration =
        dataSource.getPokemonListByGeneration(limit, offset)
            .PokemonGenerationResponseToPokemonGeneration()

    override suspend fun getPokemon(name: String): Pokemon =
        dataSource.getPokemon(name).pokemonResponseToPokemon()
*/
}