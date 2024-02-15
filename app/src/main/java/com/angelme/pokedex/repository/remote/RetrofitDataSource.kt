package com.angelme.pokedex.repository.remote

import com.angelme.pokedex.remote.PokedexService
import com.angelme.pokedex.remote.PokemonGenerationResponse
import com.angelme.pokedex.remote.PokemonResponse
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val apiService: PokedexService
)/*: RemoteDataSource {

    override suspend fun getPokemonListByGeneration(
        limit: Int,
        offset: Int
    ): PokemonGenerationResponse {
        apiService.getPokemonListByGeneration(limit, offset).execute().let {
            return it.body() ?: throw Exception("Api fails")
        }
    }

    override suspend fun getPokemon(name: String): PokemonResponse {
        apiService.getPokemon(name).execute().let {
            return it.body() ?: throw Exception("Api fails")
        }
    }
}*/