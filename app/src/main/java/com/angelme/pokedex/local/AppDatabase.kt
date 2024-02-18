package com.angelme.pokedex.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.angelme.pokedex.local.dao.PokemonDao
import com.angelme.pokedex.local.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}