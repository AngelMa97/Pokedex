package com.angelme.pokedex.repository

import com.angelme.pokedex.repository.remote.RemoteDataSource
import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.ui.model.PokemonGeneration
import com.angelme.pokedex.util.Generation
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val dataSource: RemoteDataSource
) : PokemonRepository {
    override suspend fun getPokemonByGeneration(generation: Generation): PokemonGeneration =
        dataSource.getPokemonListByGeneration(generation)

    override suspend fun getPokemon(index: Int): Pokemon = dataSource.getPokemon(index)
}
