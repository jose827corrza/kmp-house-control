package org.josedev.house_control.domain.models

data class Actuator(
    val id: String,
    val name: String,
    val isActive: Boolean,
    val isUpdating: Boolean
)
