package org.josedev.house_control.data.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActuatorResponse(
    @SerialName("house_id")
    val houseId: String,

    @SerialName("house_name")
    val houseName: String,

    @SerialName("actuator_id")
    val actuatorId: String,

    @SerialName("actuator_name")
    val actuatorName: String,

    @SerialName("actuator_state")
    val actuatorState: Boolean,
)
