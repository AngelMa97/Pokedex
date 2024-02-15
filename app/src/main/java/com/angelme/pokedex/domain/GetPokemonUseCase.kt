package com.angelme.pokedex.domain

import com.angelme.pokedex.remote.PokedexService
import com.angelme.pokedex.repository.PokemonRepository
import com.angelme.pokedex.ui.model.Pokemon
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    //private val pokemonRepository: PokemonRepository
    private val apiService: PokedexService
) {
    //suspend fun invoke(name: String): Pokemon = pokemonRepository.getPokemon(name)

    suspend operator fun invoke(index: Int) =
        apiService.getPokemon(index).body()!!.pokemonResponseToPokemon()
}