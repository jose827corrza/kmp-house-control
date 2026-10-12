package org.josedev.house_control.domain.models.auth

data class AuthenticationResult(
    val accessToken: String,
    val expiresIn: Int,
    val idToken: String,
    val refreshToken: String,
    val tokenType: String,
)
