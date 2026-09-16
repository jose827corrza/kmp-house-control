package org.josedev.house_control.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.josedev.house_control.domain.models.Actuator

@Composable
fun DeviceRowItem(
    actuator: Actuator, toggle: (String, Boolean) -> Unit
) {
    var switchStatus by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth().height(70.dp).padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (actuator.isUpdating) {
            Spacer(Modifier.width(20.dp))
            CircularProgressIndicator()
            Spacer(Modifier.width(20.dp))
        } else {
            Text(
                text = actuator.name,
            )

            Switch(
                checked = switchStatus, onCheckedChange = {
                    switchStatus = it
                    toggle(actuator.id, switchStatus)
                })

        }
    }
}