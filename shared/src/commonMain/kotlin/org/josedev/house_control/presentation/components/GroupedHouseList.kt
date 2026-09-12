package org.josedev.house_control.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import org.josedev.house_control.domain.models.Actuator

@Composable
fun GroupedHouseList(
    groupedData: Map<String, List<Actuator>>,
    onToggleActuator: (id: String, toggle: Boolean) -> Unit,
) {
    LazyColumn {
        groupedData.forEach { (groupHeader, actuatorList) ->
            stickyHeader {
                HeaderItem(groupHeader)
            }

            items(
                items = actuatorList,
                key = { key -> key.actuatorId },
            ) { actuator ->
                DeviceRowItem(actuator, toggle = { id, state ->
                    onToggleActuator(id, state)
                })
            }
        }
    }
}