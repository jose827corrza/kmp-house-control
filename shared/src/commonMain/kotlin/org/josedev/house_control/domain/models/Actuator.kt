package org.josedev.house_control.domain.models

data class Actuator(
    val houseId: String,
    val houseName: String,
    val actuatorId: String,
    val actuatorName: String,
    val actuatorState: Boolean,
    val isUpdating: Boolean
)
