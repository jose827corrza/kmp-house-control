package org.josedev.house_control.data.repository

import org.josedev.house_control.data.client.AuthApi
import org.josedev.house_control.data.client.models.auth.CognitoTokenRequestDTO
import org.josedev.house_control.data.mappers.toDomain
import org.josedev.house_control.domain.models.auth.CognitoTokenResponse
import org.josedev.house_control.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val client: AuthApi
) : AuthRepository {
    private val clientId = "4jk66a3bam5poookh0gl249s59"
    private val USERNAME_KEY = "USERNAME"
    private val PASSWORD_KEY = "PASSWORD"

    override suspend fun loginWithCredentials(
        email: String,
        password: String
    ): Result<CognitoTokenResponse> = runCatching {
        val authParameters: Map<String, String> = mutableMapOf(USERNAME_KEY to email, PASSWORD_KEY to password)
        val cognitoRequestDTO = CognitoTokenRequestDTO(
            authFlow = "USER_PASSWORD_AUTH",
            clientId = clientId,
            authParameters = authParameters
        )
        client.loginWithCredentials(cognitoRequestDTO).toDomain()
    }
}