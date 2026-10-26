package org.josedev.house_control

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    var action by remember { mutableStateOf("Last Action: None") }
    var isOpen by remember { mutableStateOf(true) }

    Window(
        onCloseRequest = ::exitApplication,
        title = "house-control",
    ) {
        MenuBar {
            Menu("File", mnemonic = 'F') {
                Item(
                    "Copy", onClick = {
                        action = "Last Action: Copy"
                    },
                    shortcut = KeyShortcut(Key.C, ctrl = true)
                )
                Item(
                    "Paste", onClick = {
                        action = "Last Action: Paste"
                    },
                    shortcut = KeyShortcut(Key.V, ctrl = true)
                )
            }
        }
        App()
    }
}