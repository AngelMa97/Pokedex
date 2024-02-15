package com.angelme.pokedex.domain

import com.angelme.pokedex.repository.PokemonRepository
import com.angelme.pokedex.ui.model.PokemonGeneration
import com.angelme.pokedex.util.Generation
import javax.inject.Inject

class GetPokemonByGenerationUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(generation: Generation): PokemonGeneration =
        pokemonRepository.getPokemonByGeneration(generation)
}