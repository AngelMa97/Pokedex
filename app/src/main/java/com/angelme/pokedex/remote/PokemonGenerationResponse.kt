package com.angelme.pokedex.remote

import com.angelme.pokedex.ui.model.PokemonGeneration

data class PokemonGenerationResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonListResponse>
) {
    fun pokemonGenerationResponseToPokemonGeneration(): PokemonGeneration =
        PokemonGeneration(
            results.map { it.name }
        )
}

data class PokemonListResponse(
    val name: String,
    val url: String
)
