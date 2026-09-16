package org.josedev.house_control.data.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActuatorDTO(
    @SerialName("is_active")
    val isActive: Boolean,

    @SerialName("name")
    val name: String
)
