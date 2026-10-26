package org.josedev.house_control.data.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActuatorResponseDTO(
    @SerialName("actuators")
    val actuators: Map<String, ActuatorDTO>
)
