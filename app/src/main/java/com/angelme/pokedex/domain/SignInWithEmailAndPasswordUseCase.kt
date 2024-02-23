package com.angelme.pokedex.domain

import com.angelme.pokedex.repository.remote.Authenticator
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    suspend operator fun invoke(email: String, password: String) =
        authenticator.signInWithEmailAndPassword(email, password)
}