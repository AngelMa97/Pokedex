package com.angelme.pokedex.domain

import com.angelme.pokedex.repository.remote.Authenticator
import javax.inject.Inject

class SendResetPasswordUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    suspend operator fun invoke(email: String) = authenticator.sendResetPassword(email)
}