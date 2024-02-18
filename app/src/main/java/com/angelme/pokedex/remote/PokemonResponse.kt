package com.angelme.pokedex.remote

import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.ui.model.Stat
import com.angelme.pokedex.util.PokemonType
import com.google.gson.annotations.SerializedName
import java.lang.Exception

data class PokemonResponse(
    val abilities: List<AbilityResponse>,
    val id: Int,
    val name: String,
    val sprites: SpriteResponse,
    val stats: List<StatResponse>,
    val types: List<TypeResponse>
) {
    fun pokemonResponseToPokemon(): Pokemon =
        Pokemon(
            abilities.map { x -> x.ability.name },
            id,
            name,
            listOf(sprites.front, sprites.frontShiny),
            stats.map { x -> Stat(x.stat.name, x.baseStat) },
            types.map { x ->
                when (x.type.name) {
                    "fighting" -> PokemonType.Fighting
                    "psychic" -> PokemonType.Psychic
                    "poison" -> PokemonType.Poison
                    "dragon" -> PokemonType.Dragon
                    "ghost" -> PokemonType.Ghost
                    "dark" -> PokemonType.Dark
                    "ground" -> PokemonType.Ground
                    "fire" -> PokemonType.Fire
                    "fairy" -> PokemonType.Fairy
                    "water" -> PokemonType.Water
                    "flying" -> PokemonType.Flying
                    "normal" -> PokemonType.Normal
                    "rock" -> PokemonType.Rock
                    "electric" -> PokemonType.Electric
                    "bug" -> PokemonType.Bug
                    "grass" -> PokemonType.Grass
                    "ice" -> PokemonType.Ice
                    "steel" -> PokemonType.Steel
                    else -> throw Exception("type not supported")
                }
            }
        )
}

data class AbilityResponse(
    val ability: AbilityInfoResponse
)

data class AbilityInfoResponse(
    val name: String
)

data class SpriteResponse(
    @SerializedName("front_default") val front: String,
    @SerializedName("front_shiny") val frontShiny: String,
)

data class StatResponse(
    @SerializedName("base_stat") val baseStat: Int,
    val stat: StatNameResponse
)

data class StatNameResponse(
    val name: String
)

data class TypeResponse(
    val type: TypeNameResponse
)

data class TypeNameResponse(
    val name: String
)
