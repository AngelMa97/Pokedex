package com.angelme.pokedex.domain

import com.angelme.pokedex.repository.remote.Authenticator
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke() = authenticator.getUser()
}