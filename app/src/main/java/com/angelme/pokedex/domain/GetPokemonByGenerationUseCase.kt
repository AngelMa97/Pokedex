package com.angelme.pokedex.domain

import com.angelme.pokedex.remote.PokedexService
import com.angelme.pokedex.repository.DefaultRepository
import com.angelme.pokedex.repository.PokemonRepository
import com.angelme.pokedex.ui.model.PokemonGeneration
import com.angelme.pokedex.util.Generation
import javax.inject.Inject

class GetPokemonByGenerationUseCase @Inject constructor(
    //private val pokemonRepository: PokemonRepository
    private val apiService: PokedexService
) {

    suspend operator fun invoke(generation: Generation): PokemonGeneration {
        val body = apiService.getPokemonListByGeneration(generation.noPokemon, generation.offset).body()
        val pokemonList = body?.pokemonGenerationResponseToPokemonGeneration()
        return pokemonList!!
    }
    //suspend fun invoke(generation: Generation): PokemonGeneration =
        //pokemonRepository.getPokemonByGeneration(generation.noPokemon, generation.offset)
}