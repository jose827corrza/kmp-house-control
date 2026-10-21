package org.josedev.house_control.data.client.models.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CognitoTokenRequestDTO(

    @SerialName("AuthFlow")
    val authFlow: String,

    @SerialName("ClientId")
    val clientId: String,

    @SerialName("AuthParameters")
    val authParameters: Map<String, String>
)
