package com.angelme.pokedex.repository

import com.angelme.pokedex.repository.local.LocalDataSource
import com.angelme.pokedex.repository.remote.RemoteDataSource
import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.ui.model.PokemonGeneration
import com.angelme.pokedex.util.Generation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : PokemonRepository {
    override suspend fun getPokemonByGeneration(generation: Generation): PokemonGeneration =
        remoteDataSource.getPokemonListByGeneration(generation)

    override suspend fun getPokemon(index: Int): Pokemon = remoteDataSource.getPokemon(index)
    override fun gotThemAll(): Flow<List<Pokemon>> =
        localDataSource.gotThemAll()

    override fun getMyPokemonById(id: Int): Flow<Pokemon?> =
        localDataSource.getMyPokemonById(id)

    override suspend fun gotPokemon(pokemon: Pokemon) =
        localDataSource.gotPokemon(pokemon)

    override suspend fun lostPokemon(pokemon: Pokemon) =
        localDataSource.lostPokemon(pokemon)
}
