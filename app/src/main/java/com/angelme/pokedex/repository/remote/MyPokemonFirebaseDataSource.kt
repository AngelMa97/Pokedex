package com.angelme.pokedex.repository.remote

import com.angelme.pokedex.ui.model.Pokemon
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class MyPokemonFirebaseDataSource @Inject constructor(
    private val databaseReference: DatabaseReference
) : RemoteMyPokemonDataSource {
    override suspend fun getStoredPokemon(): List<Pokemon> {
        var pokemonList = emptyList<Pokemon>()
        databaseReference.child("pokemon").get().addOnSuccessListener { data ->
            pokemonList = data as List<Pokemon>
        }.addOnFailureListener {
            throw it
        }
        return pokemonList
    }

    override suspend fun syncStorePokemon(pokemonList: List<Pokemon>) {
        databaseReference.setValue(pokemonList)
    }
}