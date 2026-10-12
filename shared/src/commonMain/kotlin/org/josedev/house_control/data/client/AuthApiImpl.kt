package org.josedev.house_control.data.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.headers
import org.josedev.house_control.data.client.models.auth.CognitoTokenRequestDTO
import org.josedev.house_control.data.client.models.auth.CognitoTokenResponseDTO
import org.josedev.house_control.utils.AppLogger

class AuthApiImpl(
    private val httpClient: HttpClient,
    private val logger: AppLogger,
) : AuthApi {

    override suspend fun loginWithCredentials(cognitoTokenRequestDTO: CognitoTokenRequestDTO): CognitoTokenResponseDTO {
        return httpClient.post("auth/login") {
            headers {
                append("Content-Type", "application/x-amz-json-1.1")
                append("X-Amz-Target", "AWSCognitoIdentityProviderService.InitiateAuth")
            }
            setBody{
                cognitoTokenRequestDTO
            }
        }.body()
    }
}