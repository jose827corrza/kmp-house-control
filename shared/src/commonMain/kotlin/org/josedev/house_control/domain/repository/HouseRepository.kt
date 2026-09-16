package org.josedev.house_control.domain.repository

import org.josedev.house_control.domain.models.Actuator
import org.josedev.house_control.domain.models.House

interface HouseRepository {
    suspend fun fetchHouses(token: String): Result<Map<House, List<Actuator>>>
    suspend fun updateActuatorState(id: String, newState: Boolean): Result<Unit>
}