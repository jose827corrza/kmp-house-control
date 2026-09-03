package org.josedev.house_control.utils

class JvmConsoleLogger : AppLogger {
    override fun d(tag: String, msg: String) {
        println("[$tag]: $msg")
    }
}