package com.angelme.pokedex.di

import android.app.Application
import androidx.room.Room
import com.angelme.pokedex.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) =
        Room
            .databaseBuilder(app, AppDatabase::class.java, "pokemon_db")
            .fallbackToDestructiveMigration().build()
}