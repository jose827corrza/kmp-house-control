package org.josedev.house_control.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActuatorResponse(
    @SerialName("actuator_state")
    val actuatorState: Boolean,
)
