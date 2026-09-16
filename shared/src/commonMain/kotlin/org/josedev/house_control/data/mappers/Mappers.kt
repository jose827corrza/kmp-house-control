package org.josedev.house_control.data.mappers

import org.josedev.house_control.data.client.models.ActuatorDTO
import org.josedev.house_control.data.client.models.HouseDTO
import org.josedev.house_control.domain.models.Actuator
import org.josedev.house_control.domain.models.House

fun HouseDTO.toDomain(): House {
    return House(
        houseId = this.houseId,
        houseName = this.houseName,
        actuators = this.actuators.mapValues { (key, dto) -> dto.toDomain(key) },
    )
}

fun ActuatorDTO.toDomain(key: String): Actuator {
    return Actuator(
        id = key,
        name = this.name,
        isActive = this.isActive,
        isUpdating = false
    )
}