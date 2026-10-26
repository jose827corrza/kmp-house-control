package org.josedev.house_control.data.client.models.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CognitoTokenResponseDTO(
    @SerialName("AuthenticationResult")
    val authenticationResult: AuthenticationResultDTO,

    @SerialName("ChallengeParameters")
    val challengeParameters: Map<String, String>
)
