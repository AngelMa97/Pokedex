package com.angelme.pokedex.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    suspend fun getPokemonListByGeneration(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonGenerationResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") pokemonName: Int
    ): Response<PokemonResponse>
}