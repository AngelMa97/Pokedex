package com.angelme.pokedex.di

import com.angelme.pokedex.repository.DefaultRepository
import com.angelme.pokedex.repository.PokemonRepository
import com.angelme.pokedex.repository.local.LocalDataSource
import com.angelme.pokedex.repository.local.RoomDataSource
import com.angelme.pokedex.repository.remote.MyPokemonFirebaseDataSource
import com.angelme.pokedex.repository.remote.RemoteDataSource
import com.angelme.pokedex.repository.remote.RemoteMyPokemonDataSource
import com.angelme.pokedex.repository.remote.RetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(
        defaultRepository: DefaultRepository
    ): PokemonRepository

    @Binds
    abstract fun bindRemoteDatasource(
        retrofitDataSource: RetrofitDataSource
    ): RemoteDataSource

    @Binds
    abstract fun bindLocalDatasource(
        roomDataSource: RoomDataSource
    ): LocalDataSource

    @Binds
    abstract fun bindRemoteMyPokemonDataSource(
        myPokemonFirebaseDataSource: MyPokemonFirebaseDataSource
    ): RemoteMyPokemonDataSource
}