package com.angelme.pokedex.domain

import com.angelme.pokedex.repository.PokemonRepository
import com.angelme.pokedex.ui.model.Pokemon
import javax.inject.Inject

class RemovePokemonFromMyListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(pokemon: Pokemon) = pokemonRepository.lostPokemon(pokemon)
}