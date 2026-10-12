package org.josedev.house_control.data.repository

import org.josedev.house_control.data.client.AuthApi
import org.josedev.house_control.data.client.models.auth.CognitoTokenRequestDTO
import org.josedev.house_control.data.mappers.toDomain
import org.josedev.house_control.domain.models.auth.CognitoTokenResponse
import org.josedev.house_control.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authClient: AuthApi
) : AuthRepository {
    private val clientId = "123"
    private val USERNAME_KEY = "username"
    private val PASSWORD_KEY = "password"

    override suspend fun loginWithCredentials(
        email: String,
        password: String
    ): Result<CognitoTokenResponse> = runCatching {
        val authParameters: Map<String, String> = mutableMapOf(USERNAME_KEY to email, PASSWORD_KEY to password)
        val cognitoRequestDTO = CognitoTokenRequestDTO(clientId = clientId, authParameters = authParameters)
        authClient.loginWithCredentials(cognitoRequestDTO).toDomain()
    }
}