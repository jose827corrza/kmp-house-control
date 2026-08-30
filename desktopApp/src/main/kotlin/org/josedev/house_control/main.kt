package org.josedev.house_control

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "house-control",
    ) {
        App()
    }
}