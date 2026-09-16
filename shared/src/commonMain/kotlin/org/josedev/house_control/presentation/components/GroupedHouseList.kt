package org.josedev.house_control.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import org.josedev.house_control.domain.models.Actuator
import org.josedev.house_control.domain.models.House

@Composable
fun GroupedHouseList(
    groupedData: Map<House, List<Actuator>>,
    onToggleActuator: (id: String, toggle: Boolean) -> Unit,
) {
    LazyColumn {
        groupedData.forEach { (groupHeader, actuatorList) ->
            stickyHeader {
                HeaderItem(groupHeader.houseName)
            }

            items(
                items = actuatorList,
                key = { key -> key.id },
            ) { actuator ->
                DeviceRowItem(actuator, toggle = { id, state ->
                    onToggleActuator(id, state)
                })
            }
        }
    }
}