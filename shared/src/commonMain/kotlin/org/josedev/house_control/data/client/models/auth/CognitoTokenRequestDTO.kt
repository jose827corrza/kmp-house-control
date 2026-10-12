package org.josedev.house_control.data.client.models.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CognitoTokenRequestDTO(

    @SerialName("AuthFLow")
    val authFlow: String = "USER_PASSWORD_AUTH",

    @SerialName("ClientId")
    val clientId: String,

    @SerialName("AuthParameters")
    val authParameters: Map<String, String>
)
