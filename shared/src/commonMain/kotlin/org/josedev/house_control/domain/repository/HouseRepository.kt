package org.josedev.house_control.domain.repository

import org.josedev.house_control.domain.models.Actuator

interface HouseRepository {
    suspend fun fetchHouses(token: String): Result<Map<String,List<Actuator>>>
    suspend fun updateActuatorState(id: String, newState: Boolean): Result<Unit>
}