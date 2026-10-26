package org.josedev.house_control.domain.repository

import org.josedev.house_control.domain.models.Actuator
import org.josedev.house_control.domain.models.ActuatorRequest
import org.josedev.house_control.domain.models.House

interface HouseRepository {
    suspend fun fetchHouses(): Result<Map<House, List<Actuator>>>
    suspend fun updateActuatorState(actuatorRequest: ActuatorRequest): Result<Actuator>
}