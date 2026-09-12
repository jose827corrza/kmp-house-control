package org.josedev.house_control.data.mappers

import org.josedev.house_control.data.client.models.ActuatorResponse
import org.josedev.house_control.domain.models.Actuator

fun ActuatorResponse.toDomain(): Actuator {
    return Actuator(
        houseId = this.houseId,
        houseName = this.houseName,
        actuatorId = this.actuatorId,
        actuatorName = this.actuatorName,
        actuatorState = this.actuatorState,
        isUpdating = false
    )
}