package com.angelme.pokedex.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.angelme.pokedex.remote.AbilityResponse
import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.ui.model.Stat
import com.angelme.pokedex.util.PokemonType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
class PokemonEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo
    val abilities: String,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val sprites: String,
    @ColumnInfo
    val stats: String,
    @ColumnInfo
    val types: String,
    @ColumnInfo
    val gotIt: Boolean
) {
    fun pokemonEntityToPokemon(): Pokemon {
        val listString = object : TypeToken<List<String>>() {}.type
        val listStat = object : TypeToken<List<Stat>>() {}.type
        val listType = object : TypeToken<List<PokemonType>>() {}.type
        return Pokemon(
            Gson().fromJson(abilities, listString),
            id,
            name,
            Gson().fromJson(sprites, listString),
            Gson().fromJson(stats, listStat),
            Gson().fromJson(types, listType),
            true
        )
    }
}
