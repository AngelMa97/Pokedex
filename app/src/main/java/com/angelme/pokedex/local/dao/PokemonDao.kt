package com.angelme.pokedex.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.angelme.pokedex.local.entity.PokemonEntity
import com.angelme.pokedex.ui.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonentity")
    fun gotThemAll(): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemonentity WHERE id = :id")
    fun getPokemonById(id: Int): Flow<PokemonEntity?>

    @Insert
    suspend fun gotPokemon(pokemon: PokemonEntity)

    @Delete
    suspend fun lostPokemon(pokemon: PokemonEntity)

}