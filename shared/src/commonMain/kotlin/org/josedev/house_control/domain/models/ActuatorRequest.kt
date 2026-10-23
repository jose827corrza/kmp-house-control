package org.josedev.house_control.domain.models

data class ActuatorRequest(
    val houseId: String,

    val actuatorId: String,

    val actuatorState: Boolean
)
