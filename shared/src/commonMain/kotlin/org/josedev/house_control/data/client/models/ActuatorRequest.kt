package org.josedev.house_control.data.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActuatorRequest(
    @SerialName("actuator_id")
    val actuatorId: String,

    @SerialName("actuator_state")
    val actuatorState: Boolean
)
