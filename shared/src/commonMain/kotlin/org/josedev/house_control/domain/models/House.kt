package org.josedev.house_control.domain.models

data class House(
    val houseId: String,
    val houseName: String,
    val actuators: Map<String, Actuator>
)
