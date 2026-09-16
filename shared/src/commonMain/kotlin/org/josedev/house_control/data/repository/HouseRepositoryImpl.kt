package org.josedev.house_control.data.repository

import org.josedev.house_control.data.client.ClientApi
import org.josedev.house_control.data.client.models.ActuatorRequest
import org.josedev.house_control.data.mappers.toDomain
import org.josedev.house_control.domain.models.Actuator
import org.josedev.house_control.domain.models.House
import org.josedev.house_control.domain.repository.HouseRepository

class HouseRepositoryImpl(
    private val client: ClientApi
) : HouseRepository {
    override suspend fun fetchHouses(token: String): Result<Map<House, List<Actuator>>> = runCatching {
        client.fetchHouses("TOKEN")
            .associate { houseDTO ->
                val house = houseDTO.toDomain()
                house to house.actuators.values.toList()
            }
    }

    override suspend fun updateActuatorState(id: String, newState: Boolean): Result<Unit> = runCatching {
        client.sendActuatorState("", ActuatorRequest(id, newState))
    }
}