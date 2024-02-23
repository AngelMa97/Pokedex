package com.angelme.pokedex.repository.remote

import com.google.firebase.auth.FirebaseUser

interface Authenticator {

    suspend fun signInWithEmailAndPassword(email: String, password: String): FirebaseUser?

    suspend fun signUpWithEmailAndPassword(email: String, password: String): FirebaseUser?

    fun signOut(): FirebaseUser?

    fun getUser(): FirebaseUser?

    suspend fun sendResetPassword(email: String)
}