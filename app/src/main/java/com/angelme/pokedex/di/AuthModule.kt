package com.angelme.pokedex.di

import com.angelme.pokedex.repository.remote.Authenticator
import com.angelme.pokedex.repository.remote.FirebaseAuthenticator
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Singleton
    @Provides
    fun provideAuthenticator(firebaseAuth: FirebaseAuth): Authenticator =
        FirebaseAuthenticator(firebaseAuth)

}
