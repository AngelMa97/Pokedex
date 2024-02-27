package com.angelme.pokedex.domain

import com.angelme.pokedex.repository.PokemonRepository
import javax.inject.Inject

class GetMyPokemonRemoteUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke() = pokemonRepository.getStoredPokemon()
}