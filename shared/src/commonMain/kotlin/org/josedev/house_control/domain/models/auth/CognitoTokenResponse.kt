package org.josedev.house_control.domain.models.auth

data class CognitoTokenResponse(
    val authenticationResult: AuthenticationResult,
    val challengeParameters: Map<String, String>
)
