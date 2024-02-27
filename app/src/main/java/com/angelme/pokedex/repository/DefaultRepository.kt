package com.angelme.pokedex.repository

import com.angelme.pokedex.repository.local.LocalDataSource
import com.angelme.pokedex.repository.remote.Authenticator
import com.angelme.pokedex.repository.remote.RemoteDataSource
import com.angelme.pokedex.repository.remote.RemoteMyPokemonDataSource
import com.angelme.pokedex.ui.model.Pokemon
import com.angelme.pokedex.ui.model.PokemonGeneration
import com.angelme.pokedex.util.Generation
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val authenticator: Authenticator,
    private val remoteMyPokemonDataSource: RemoteMyPokemonDataSource
) : PokemonRepository, Authenticator {
    override suspend fun getPokemonByGeneration(generation: Generation): PokemonGeneration =
        remoteDataSource.getPokemonListByGeneration(generation)

    override suspend fun getPokemon(index: Int): Pokemon = remoteDataSource.getPokemon(index)
    override fun gotThemAll(): Flow<List<Pokemon>> =
        localDataSource.gotThemAll()

    override fun getMyPokemonById(id: Int): Flow<Pokemon?> =
        localDataSource.getMyPokemonById(id)

    override suspend fun gotPokemon(pokemon: Pokemon) =
        localDataSource.gotPokemon(pokemon)

    override suspend fun lostPokemon(pokemon: Pokemon) =
        localDataSource.lostPokemon(pokemon)

    override suspend fun getStoredPokemon(): List<Pokemon> =
        remoteMyPokemonDataSource.getStoredPokemon()

    override suspend fun syncStorePokemon(pokemonList: List<Pokemon>) =
        remoteMyPokemonDataSource.syncStorePokemon(pokemonList)

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): FirebaseUser? = authenticator.signInWithEmailAndPassword(email, password)

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): FirebaseUser? = authenticator.signUpWithEmailAndPassword(email, password)

    override fun signOut(): FirebaseUser? = authenticator.signOut()

    override fun getUser(): FirebaseUser? = authenticator.getUser()

    override suspend fun sendResetPassword(email: String) = authenticator.sendResetPassword(email)
}
