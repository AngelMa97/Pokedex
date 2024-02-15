package com.angelme.pokedex.repository.remote

import com.angelme.pokedex.remote.PokedexService
import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.ui.model.PokemonGeneration
import com.angelme.pokedex.util.Generation
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val apiService: PokedexService
) : RemoteDataSource {

    override suspend fun getPokemonListByGeneration(
        generation: Generation
    ): PokemonGeneration {
        val body =
            apiService.getPokemonListByGeneration(generation.noPokemon, generation.offset).body()
        val pokemonList = body?.pokemonGenerationResponseToPokemonGeneration()
        return pokemonList!!
    }

    override suspend fun getPokemon(index: Int): Pokemon =
        apiService.getPokemon(index).body()!!.pokemonResponseToPokemon()
}
