package org.josedev.house_control.data.client

import org.josedev.house_control.data.client.models.ActuatorRequestDTO
import org.josedev.house_control.data.client.models.HouseDTO

interface ClientApi {
    suspend fun sendActuatorState(token: String, actuatorRequestDTO: ActuatorRequestDTO)
    suspend fun fetchHouses(): List<HouseDTO>
    suspend fun fetchActuatorsByHouseId(token: String, houseId: String): List<HouseDTO>
}