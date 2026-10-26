package org.josedev.house_control.data.client.models.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResultDTO(
    @SerialName("AccessToken")
    val accessToken: String,

    @SerialName("ExpiresIn")
    val expiresIn: Int,

    @SerialName("IdToken")
    val idToken: String,

    @SerialName("RefreshToken")
    val refreshToken: String,

    @SerialName("TokenType")
    val tokenType: String,
)
