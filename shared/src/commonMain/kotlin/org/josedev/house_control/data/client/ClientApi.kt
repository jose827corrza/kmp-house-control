package org.josedev.house_control.data.client

import org.josedev.house_control.data.client.models.ActuatorRequestDTO
import org.josedev.house_control.data.client.models.ActuatorResponseDTO
import org.josedev.house_control.data.client.models.HouseDTO

interface ClientApi {
    suspend fun updateActuatorState(actuatorRequestDTO: ActuatorRequestDTO): ActuatorResponseDTO
    suspend fun fetchHouses(): List<HouseDTO>
    suspend fun fetchActuatorsByHouseId(token: String, houseId: String): List<HouseDTO>
}