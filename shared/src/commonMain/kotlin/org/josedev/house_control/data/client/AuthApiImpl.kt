package org.josedev.house_control.data.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.josedev.house_control.data.client.models.auth.CognitoTokenRequestDTO
import org.josedev.house_control.data.client.models.auth.CognitoTokenResponseDTO
import org.josedev.house_control.utils.AppLogger

class AuthApiImpl(
    private val httpClient: HttpClient,
    private val logger: AppLogger,
) : AuthApi {

    val AwsJson = ContentType.parse("application/x-amz-json-1.1")

    override suspend fun loginWithCredentials(cognitoTokenRequestDTO: CognitoTokenRequestDTO): CognitoTokenResponseDTO {
        return httpClient.post() {
            header("X-Amz-Target", "AWSCognitoIdentityProviderService.InitiateAuth")
            contentType(AwsJson)
            setBody(cognitoTokenRequestDTO)
        }.body()
    }
}