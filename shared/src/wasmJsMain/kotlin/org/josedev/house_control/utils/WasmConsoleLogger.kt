package org.josedev.house_control.utils

class WasmConsoleLogger : AppLogger {
    override fun d(tag: String, msg: String) {
        println("[$tag] $msg")
    }
}