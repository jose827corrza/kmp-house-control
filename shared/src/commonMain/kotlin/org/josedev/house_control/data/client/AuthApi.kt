package org.josedev.house_control.data.client

import org.josedev.house_control.data.client.models.auth.CognitoTokenRequestDTO
import org.josedev.house_control.data.client.models.auth.CognitoTokenResponseDTO

interface AuthApi {

    suspend fun loginWithCredentials(cognitoTokenRequestDTO: CognitoTokenRequestDTO): CognitoTokenResponseDTO
}