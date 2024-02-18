package com.angelme.pokedex.domain

import com.angelme.pokedex.repository.PokemonRepository
import com.angelme.pokedex.ui.model.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyPokemonByIdUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(id: Int): Flow<Pokemon?> = pokemonRepository.getMyPokemonById(id)
}